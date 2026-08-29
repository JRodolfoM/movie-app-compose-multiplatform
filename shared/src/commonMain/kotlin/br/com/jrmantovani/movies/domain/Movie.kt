package br.com.jrmantovani.movies.domain

import br.com.jrmantovani.movies.data.network.IMAGE_SMALL_BASE_URL
import br.com.jrmantovani.movies.data.network.model.MoviesResponse

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
)


fun MoviesResponse.toModel() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterPath = "$IMAGE_SMALL_BASE_URL${this.posterPath}"
)


//fake objects
val movie1= Movie(
    id =1,
    title = "Homem-Aranha: Um Novo Dia",
    overview = "Movimento sem domar",
    posterPath = "https://image.tmdb.org/t/p/w154/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"
)