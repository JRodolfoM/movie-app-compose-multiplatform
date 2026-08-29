package br.com.jrmantovani.movies


import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.*

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.jrmantovani.movies.navigation.AppRoutes
import br.com.jrmantovani.movies.ui.features.movie.MoviesListScreenRoute


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = AppRoutes.MoviesList) {
            composable<AppRoutes.MoviesList> {
                MoviesListScreenRoute()
            }
            composable<AppRoutes.MovieDetails> {
                //MovieDetailsScreenRoute()
            }
        }
    }
}