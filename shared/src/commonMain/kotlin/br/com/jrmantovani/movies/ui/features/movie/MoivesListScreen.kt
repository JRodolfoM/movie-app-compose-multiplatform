package br.com.jrmantovani.movies.ui.features.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.jrmantovani.movies.data.repository.MovieRepository
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.domain.MovieSection
import br.com.jrmantovani.movies.domain.movie1
import br.com.jrmantovani.movies.ui.components.MovieSectionComp
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme
import io.ktor.websocket.Frame
import movies.shared.generated.resources.Res
import movies.shared.generated.resources.movies_list_popular_movies
import movies.shared.generated.resources.movies_list_top_rated_movies
import movies.shared.generated.resources.movies_list_upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MoviesListScreenRoute(
    viewModel: MoviesListViewModel = koinViewModel (),
    navigateToMovieDetails: (movieId: Int) -> Unit,
) {
   val moviesListState by viewModel.moviesListState.collectAsStateWithLifecycle()


    MoviesListScreen(
        moviesListState = moviesListState,
        onMovieClick = navigateToMovieDetails

    )
}


@Composable
fun MoviesListScreen(
    moviesListState: MoviesListViewModel.MoviesListState,
    onMovieClick: (movieId: Int) -> Unit,
) {

    Scaffold (){padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)

        ){

            when(moviesListState){

                MoviesListViewModel.MoviesListState.Loading ->{
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align (Alignment.Center)
                    )
                }
                is MoviesListViewModel.MoviesListState.Success -> {
                    LazyColumn (modifier = Modifier
                        .padding(padding),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ){
                        items(moviesListState.moviesSection){ moviSection ->
                            val title = when(moviSection.sectionType){
                                MovieSection.SectionType.POPULAR -> stringResource(Res.string.movies_list_popular_movies)
                                MovieSection.SectionType.TOP_RATED -> stringResource(Res.string.movies_list_top_rated_movies)
                                MovieSection.SectionType.UPCOMING -> stringResource(Res.string.movies_list_upcoming_movies)
                            }

                           MovieSectionComp(
                                title = title,
                                movies = moviSection.movies,
                                onMoviePosterClick = onMovieClick
                            )

                        }





                    }
                }
                is MoviesListViewModel.MoviesListState.Error -> {
                    Text(
                        text = moviesListState.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }

}


@Preview
@Composable
private fun MoviesListScreenPreview() {
    MoviesAppTheme{
        MoviesListScreen(moviesListState =  MoviesListViewModel.MoviesListState.Success(
            listOf(MovieSection(sectionType = MovieSection.SectionType.POPULAR, movies = listOf(movie1, movie1, movie1)))),

                onMovieClick = {}
        )


        }
}

@Preview
@Composable
private fun MoviesListScreenErrorPreview() {
    MoviesAppTheme{
        MoviesListScreen(moviesListState =  MoviesListViewModel.MoviesListState.Error("Error"), onMovieClick = {})
    }
}

@Preview
@Composable
private fun MoviesListScreenLoadingPreview() {
    MoviesAppTheme{
        MoviesListScreen(moviesListState =  MoviesListViewModel.MoviesListState.Loading, onMovieClick = {})
    }
}