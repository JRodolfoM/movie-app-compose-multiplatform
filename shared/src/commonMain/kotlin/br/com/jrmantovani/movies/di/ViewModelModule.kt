package br.com.jrmantovani.movies.di

import br.com.jrmantovani.movies.ui.features.movie.MoviesListViewModel
import br.com.jrmantovani.movies.ui.features.moviedetail.MovieDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val viewModelsModule = module {
    viewModel { MoviesListViewModel(get()) }
    viewModel{ MovieDetailViewModel(get(), get()) }
}