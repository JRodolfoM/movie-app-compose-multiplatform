package br.com.jrmantovani.movies.ui.features.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jrmantovani.movies.data.repository.MovieRepository
import br.com.jrmantovani.movies.domain.MovieSection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel (
    private val movieRepository: MovieRepository
) : ViewModel(){


    private val _moviesListState = MutableStateFlow<MoviesListState>(MoviesListState.Loading)
    val moviesListState = _moviesListState.asStateFlow()

    init {
        getMovieSections()
    }

    private fun getMovieSections() {
      viewModelScope.launch {
          try {
              val movieSections = movieRepository.getMovieSections()
              _moviesListState.update {
                  MoviesListState.Success(movieSections)
              }
          }catch (e: Exception){
              _moviesListState.update {
                  MoviesListState.Error(e.message ?: "Unknown error")
              }
          }
      }
    }

    sealed interface MoviesListState{
        data object Loading: MoviesListState
        data class Success(val moviesSection: List<MovieSection>): MoviesListState
        data class Error(val message: String): MoviesListState
    }

}