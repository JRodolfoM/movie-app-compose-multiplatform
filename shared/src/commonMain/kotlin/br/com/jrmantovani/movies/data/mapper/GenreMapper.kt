package br.com.jrmantovani.movies.data.mapper

import br.com.jrmantovani.movies.data.network.model.GenreResponse
import br.com.jrmantovani.movies.domain.Genre

fun GenreResponse.toModel() = Genre(
    id = this.id,
    name = this.name
)