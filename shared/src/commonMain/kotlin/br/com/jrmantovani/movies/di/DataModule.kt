package br.com.jrmantovani.movies.di

import br.com.jrmantovani.movies.data.repository.MovieRepository
import org.koin.dsl.module

val dataModule = module {
    factory { MovieRepository(get()) }
}