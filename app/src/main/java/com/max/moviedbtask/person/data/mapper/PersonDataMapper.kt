package com.max.moviedbtask.person.data.mapper

import com.max.moviedbtask.core.mapper.Mapper
import com.max.moviedbtask.person.data.rest.response.PersonResponse
import com.max.moviedbtask.person.domain.model.Person

class PersonDataMapper : Mapper<PersonResponse, Person> {
    override fun map(origin: PersonResponse) =
        Person(
            origin.id ?: 0,
            origin.name ?: "",
            origin.popularity ?: 0.0,
            origin.known_for_department ?: "",
            origin.profile_path ?: "",
            origin.known_for ?: mutableListOf()
        )
}