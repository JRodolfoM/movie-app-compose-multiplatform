package br.com.jrmantovani.movies.data.network

import br.com.jrmantovani.movies.data.network.model.CastMemberListResponse
import br.com.jrmantovani.movies.data.network.model.MovieResponse
import br.com.jrmantovani.movies.data.network.model.MoviesListResponse

class MovieNetworkDataSourceImpl(
    private val ktorClient: KtorClient
) : MovieNetworkDataSource {
    override suspend fun getMovies(category: String): MoviesListResponse {
        return ktorClient.getMovies(category)
    }

    override suspend fun getMovieDetail(movieId: Int): MovieResponse {
        return ktorClient.getMovieDetail(movieId)
    }

    override suspend fun getCredits(movieId: Int): CastMemberListResponse {
       return  ktorClient.getCredits(movieId)
    }
}
