package br.com.jrmantovani.movies.data.network

import br.com.jrmantovani.movies.data.network.model.CastMemberListResponse
import br.com.jrmantovani.movies.data.network.model.MovieResponse
import br.com.jrmantovani.movies.data.network.model.MoviesListResponse

interface MovieNetworkDataSource {
    suspend fun getMovies(category: String): MoviesListResponse
    suspend fun getMovieDetail(movieId: Int): MovieResponse
    suspend fun getCredits(movieId: Int): CastMemberListResponse
}
