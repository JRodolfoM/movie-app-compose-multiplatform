package br.com.jrmantovani.movies.data.repository

import br.com.jrmantovani.movies.data.mapper.toModel
import br.com.jrmantovani.movies.data.network.MovieNetworkDataSource
import br.com.jrmantovani.movies.domain.ImageSize
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.domain.MovieSection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getMovieSections(): List<MovieSection>{
        return withContext(ioDispatcher){
            val popularMoviesDeferred = async {  movieNetworkDataSource.getMovies("popular") }
            val topRatedMoviesDeferred = async {  movieNetworkDataSource.getMovies("top_rated") }
            val upcomingMoviesDeferred = async {  movieNetworkDataSource.getMovies("upcoming") }

            val popularMovies = popularMoviesDeferred.await()
            val topRatedMovies = topRatedMoviesDeferred.await()
            val upcomingMovies = upcomingMoviesDeferred.await()

            listOf(
                MovieSection(
                    sectionType = MovieSection.SectionType.POPULAR,
                    movies = popularMovies.results.map { it.toModel() }
                ),

                MovieSection(
                    sectionType = MovieSection.SectionType.TOP_RATED,
                    movies = topRatedMovies.results.map { it.toModel() }
                ),
                MovieSection(
                    sectionType = MovieSection.SectionType.UPCOMING,
                    movies = upcomingMovies.results.map { it.toModel() }
                )
            )



        }
    }

    suspend fun getMovieDetail(movieId: Int): Result<Movie>{
        return withContext(ioDispatcher){

            runCatching {

                val movieDetailDeferred  = async { movieNetworkDataSource.getMovieDetail(movieId) }
                val creditsDeferred= async { movieNetworkDataSource.getCredits(movieId) }
                val videosDeferred = async { movieNetworkDataSource.getVideos(movieId) }

                val movieDetailResponse = movieDetailDeferred.await()
                val creditsResponse = creditsDeferred.await()
                val videosResponse = videosDeferred.await()

                val movieTraulerYoutubeKey = videosResponse.results.firstOrNull{ videoResponse ->
                    videoResponse.site == "YouTube" && videoResponse.type == "Trailer" && videoResponse.official

                }?.key

                movieDetailResponse.toModel(
                    castMembersResponse = creditsResponse.cast,
                    movieTrailerYoutubeKey = movieTraulerYoutubeKey,
                    imageSize = ImageSize.X_LARGE
                    )
            }

            }


    }

    suspend fun isEmbeddable(videoId: String): Boolean {
        return withContext(ioDispatcher) {
            movieNetworkDataSource.isEmbeddable(videoId)
        }

    }
}