package br.com.jrmantovani.movies.domain


data class CastMember (
    val id: Int,
    val mainRole: String,
    val name: String,
    val character: String,
    val profilePath: String?

)

val castMember1 = CastMember(
    id = 1,
    mainRole = "Actor",
    name = "Tom Hanks",
    character = "Woody",
    profilePath = "/n31VRDodba6qu79pQzdUwEJOPsz.jpg"
)

val castMember2 = CastMember(
    id = 2,
    mainRole = "Actor",
    name = "Tim Allen",
    character = "Buzz Lightyear",
    profilePath = "/6qlDZWivPVzXcrYqTezlnue9dXP.jpg"
)