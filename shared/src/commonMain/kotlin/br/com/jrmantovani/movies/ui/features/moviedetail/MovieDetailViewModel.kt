package br.com.jrmantovani.movies.ui.features.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.com.jrmantovani.movies.data.repository.MovieRepository
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository
): ViewModel(){
    private val movieDetailRoute = savedStateHandle.toRoute<AppRoutes.MovieDetails>()

    private val _movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailState = _movieDetailState

    init {
        getMovieDetail()
    }

    private fun getMovieDetail(){
        viewModelScope.launch {
            movieRepository.getMovieDetail(movieDetailRoute.movieId)
                .onSuccess { movie ->
                    _movieDetailState.value = MovieDetailState.Success(movie)
                }
                .onFailure { error ->
                    _movieDetailState.value = MovieDetailState.Error(error.message ?: "Unknown error")
                }
        }
    }

    sealed interface MovieDetailState{
        data object Loading: MovieDetailState
        data class Success(val movie: Movie): MovieDetailState
        data class Error(val message: String): MovieDetailState

    }

}