package com.max.moviedbtask.person.domain.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonDetails (
    val birthday: String = "",
    val deathday: String = "",
    val gender: Int = 0,
    val biography: String = "",
    val placeOfBirth: String = ""
) : Person()