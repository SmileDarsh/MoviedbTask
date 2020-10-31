package com.max.moviedbtask.person.data.mapper

import com.max.moviedbtask.core.mapper.Mapper
import com.max.moviedbtask.person.data.rest.response.PersonDetailsResponse
import com.max.moviedbtask.person.domain.model.PersonDetails

class PersonDetailsDataMapper : Mapper<PersonDetailsResponse, PersonDetails> {
    override fun map(origin: PersonDetailsResponse): PersonDetails {
        val personDetails = PersonDetails(
            origin.birthday ?: "",
            origin.deathday ?: "",
            origin.gender ?: 0,
            origin.biography ?: "",
            origin.place_of_birth ?: ""
        )
        personDetails.id = origin.id ?: 0
        personDetails.name = origin.name ?: ""
        personDetails.popularity = origin.popularity ?: 0.0
        personDetails.knownForDepartment = origin.known_for_department ?: ""
        personDetails.image = origin.profile_path ?: ""
        return personDetails;
    }
}
