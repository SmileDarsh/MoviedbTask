package com.max.moviedbtask.person.data.rest.response

import com.max.moviedbtask.person.domain.model.KnownFor

data class PersonResponse(
    val id: Int? = null,
    val name: String? = null,
    val popularity: Double? = null,
    val known_for_department: String? = null,
    val profile_path: String? = null,
    val known_for : MutableList<KnownFor>? = null
)