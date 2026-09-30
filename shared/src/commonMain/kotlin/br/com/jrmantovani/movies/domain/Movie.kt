package br.com.jrmantovani.movies.domain


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val genres: List<Genre>?,
    val year: Int,
    val duration: String?,
    val rating:String,
    val castMembers: List<CastMember>?,
    val movieTrailerYoutubeKey: String?
    )



//fake objects
val movie1= Movie(
    id =1,
    title = "Homem-Aranha: Um Novo Dia",
    overview = "orem ipsum dolor sit amet. Est voluptas delectus hic sint natus nam optio harum et enim modi et eveniet aliquid est obcaecati nihil. Sed possimus ipsa qui sunt laboriosam et nemo rerum qui quae ducimus sit animi velit. ",
    posterPath = "https://image.tmdb.org/t/p/w154/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
    genres = listOf(Genre(id = 1, name = "Ação"), Genre(id = 2, name = "Aventura")),
    year = 2024,
    duration = "1234",
    rating = "8.9",
    castMembers = listOf(castMember1, castMember2),
    movieTrailerYoutubeKey = "1234"
)