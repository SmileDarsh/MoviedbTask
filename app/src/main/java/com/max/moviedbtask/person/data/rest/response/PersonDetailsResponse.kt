package com.max.moviedbtask.person.data.rest.response

data class PersonDetailsResponse(
    val id: Int? = null,
    val name: String? = null,
    val popularity: Double? = null,
    val known_for_department: String? = null,
    val profile_path: String? = null,
    val birthday: String? = null,
    val deathday: String? = null,
    val gender: Int? = null,
    val biography: String? = null,
    val place_of_birth: String? = null
)