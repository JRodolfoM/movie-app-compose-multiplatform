package br.com.jrmantovani.movies.data.mapper

import br.com.jrmantovani.movies.data.network.IMAGE_BASE_URL
import br.com.jrmantovani.movies.data.network.model.CastMemberResponse
import br.com.jrmantovani.movies.domain.CastMember
import br.com.jrmantovani.movies.domain.ImageSize

fun CastMemberResponse.toModel() = CastMember(
    id = this.id,
    mainRole = this.department,
    name = this.name,
    character = this.character,
    profilePath = "$IMAGE_BASE_URL/${ImageSize.SMALL.size}/${this.profilePath}"
)
