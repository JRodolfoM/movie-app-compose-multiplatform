package br.com.jrmantovani.movies.data.network

import br.com.jrmantovani.movies.data.network.model.MoviesListResponse

interface MovieNetworkDataSource {
    suspend fun getMovies(category: String): MoviesListResponse
}
