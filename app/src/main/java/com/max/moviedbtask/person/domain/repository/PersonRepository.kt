package com.max.moviedbtask.person.domain.repository

import com.max.moviedbtask.person.domain.model.Person
import com.max.moviedbtask.person.domain.model.PersonDetails
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPopularPersons(page: Int): Flow<Person>
    fun getPersonDetails(personId: Int): Flow<PersonDetails>
    fun getPersonImages(personId: Int): Flow<String>
}