package br.com.jrmantovani.movies.navigation

sealed interface AppRoutes {
    data object MoviesList: AppRoutes
    data class  MovieDetails(val movieId: String): AppRoutes

}