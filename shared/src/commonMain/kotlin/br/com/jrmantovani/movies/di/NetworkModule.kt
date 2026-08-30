package br.com.jrmantovani.movies.di

import br.com.jrmantovani.movies.data.network.KtorClient
import br.com.jrmantovani.movies.data.network.MovieNetworkDataSourceImpl
import br.com.jrmantovani.movies.data.network.MovieNetworkDataSource
import org.koin.dsl.module

val networkClient = module {
    single { KtorClient() }
    single<MovieNetworkDataSource> { MovieNetworkDataSourceImpl(get()) }
}