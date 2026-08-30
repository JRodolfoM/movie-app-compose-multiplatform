package br.com.jrmantovani.movies.data.network

import br.com.jrmantovani.movies.data.network.model.MoviesListResponse

class MovieNetworkDataSourceImpl(
    private val ktorClient: KtorClient
) : MovieNetworkDataSource {
    override suspend fun getMovies(category: String): MoviesListResponse {
        return ktorClient.getMovies(category)
    }
}
