package com.max.moviedbtask.person.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Person(
    var id: Int = 0,
    var name: String = "",
    var popularity: Double = 0.0,
    var knownForDepartment: String = "",
    var image: String = "",
    var knownFor: MutableList<KnownFor> = mutableListOf()
) : Parcelable

@Parcelize
data class KnownFor(val title: String = "") : Parcelable