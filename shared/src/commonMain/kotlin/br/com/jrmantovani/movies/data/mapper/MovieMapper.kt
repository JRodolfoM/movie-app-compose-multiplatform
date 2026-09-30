package br.com.jrmantovani.movies.data.mapper

import br.com.jrmantovani.movies.data.network.IMAGE_BASE_URL
import br.com.jrmantovani.movies.data.network.model.CastMemberResponse
import br.com.jrmantovani.movies.data.network.model.MovieResponse
import br.com.jrmantovani.movies.domain.ImageSize
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.utils.formatRating
import kotlin.math.roundToInt

fun MovieResponse.toModel(
    castMembersResponse: List<CastMemberResponse>?=null,
    movieTrailerYoutubeKey: String? = null,
    imageSize: ImageSize = ImageSize.SMALL
) = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterPath = "$IMAGE_BASE_URL/${imageSize.size}/${this.posterPath}",
    genres = this.genres?.map { it.toModel() },
    year =  this.getYearFromReleaseDate(),
    duration = this.getDurationInHoursAndMinutes() ,
    rating =  this.voteAverage.formatRating(),
    castMembers = castMembersResponse?.filter{it.department == "Acting"}?.take(20)?.map { it.toModel()},
    movieTrailerYoutubeKey = movieTrailerYoutubeKey
)

private fun MovieResponse.getYearFromReleaseDate(): Int {
    return this.releaseDate.year
}

private fun MovieResponse.getDurationInHoursAndMinutes(): String? {
    return  this.runtime?.let { runtimeMinutes ->
        val hours = runtimeMinutes / 60
        val minutes = runtimeMinutes % 60

        buildString{
            if (hours > 0) {
                append("$hours h ")
            }
            append("$minutes min")
        }
    }

}