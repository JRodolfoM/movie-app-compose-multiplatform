package br.com.jrmantovani.movies




import androidx.compose.runtime.*

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.jrmantovani.movies.di.dataModule
import br.com.jrmantovani.movies.di.networkClient
import br.com.jrmantovani.movies.di.viewModelsModule
import br.com.jrmantovani.movies.navigation.AppRoutes
import br.com.jrmantovani.movies.ui.features.movie.MoviesListScreenRoute
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration


@Composable
@Preview
fun App() {

    KoinApplication(configuration = koinConfiguration(declaration = { modules(networkClient, dataModule, viewModelsModule) }), content = {
        MoviesAppTheme{
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

    })


}