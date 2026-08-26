package br.com.jrmantovani.movies.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {
    @Serializable
    data object MoviesList: AppRoutes
   @Serializable
    data class  MovieDetails(val movieId: String): AppRoutes

}