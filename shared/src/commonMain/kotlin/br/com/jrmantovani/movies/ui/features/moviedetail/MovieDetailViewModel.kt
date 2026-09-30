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

    private val _trailerState = MutableStateFlow<TrailerState>(TrailerState.Idle)
    val trailerState = _trailerState

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

    fun isEmbeddable(videoId: String){
        viewModelScope.launch {
            _trailerState.value = TrailerState.Loading
            val response = movieRepository.isEmbeddable(videoId)

            if(response){
                _trailerState.value = TrailerState.Success(true)
            }else{
                _trailerState.value = TrailerState.Success(false)
            }
        }
    }


    sealed interface TrailerState{
        data object Idle: TrailerState
        data object Loading: TrailerState
        data class Success(val status: Boolean): TrailerState
        data class Error(val message: String): TrailerState
    }


    sealed interface MovieDetailState{
        data object Loading: MovieDetailState
        data class Success(val movie: Movie): MovieDetailState
        data class Error(val message: String): MovieDetailState

    }

}