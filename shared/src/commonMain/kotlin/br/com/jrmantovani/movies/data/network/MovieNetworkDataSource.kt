package br.com.jrmantovani.movies.data.network

import br.com.jrmantovani.movies.data.network.model.CastMemberListResponse
import br.com.jrmantovani.movies.data.network.model.MovieResponse
import br.com.jrmantovani.movies.data.network.model.MoviesListResponse
import br.com.jrmantovani.movies.data.network.model.VieosListResponse

interface MovieNetworkDataSource {
    suspend fun getMovies(category: String): MoviesListResponse
    suspend fun getMovieDetail(movieId: Int): MovieResponse
    suspend fun getCredits(movieId: Int): CastMemberListResponse

    suspend fun getVideos(movieId: Int): VieosListResponse

    suspend fun isEmbeddable(videoId: String): Boolean
}
