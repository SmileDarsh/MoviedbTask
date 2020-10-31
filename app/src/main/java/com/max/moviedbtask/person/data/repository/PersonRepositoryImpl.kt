package com.max.moviedbtask.person.data.repository

import com.max.moviedbtask.person.data.mapper.PersonDataMapper
import com.max.moviedbtask.person.data.mapper.PersonDetailsDataMapper
import com.max.moviedbtask.person.data.mapper.PersonImageDataMapper
import com.max.moviedbtask.person.data.rest.PersonRestDataStore
import com.max.moviedbtask.person.domain.model.Person
import com.max.moviedbtask.person.domain.model.PersonDetails
import com.max.moviedbtask.person.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PersonRepositoryImpl(
    private val personRestDataStore: PersonRestDataStore
) : PersonRepository {
    private val mPersonDataMapper by lazy { PersonDataMapper() }
    private val mPersonDetailsDataMapper by lazy { PersonDetailsDataMapper() }
    private val mPersonImageDataMapper by lazy { PersonImageDataMapper() }

    override fun getPopularPersons(page: Int): Flow<Person> =
        personRestDataStore.getPopularPersons(page).map { mPersonDataMapper.map(it) }

    override fun getPersonDetails(personId: Int): Flow<PersonDetails> =
        personRestDataStore.getPersonDetails(personId).map { mPersonDetailsDataMapper.map(it) }

    override fun getPersonImages(personId: Int): Flow<String> =
        personRestDataStore.getPersonImages(personId).map { mPersonImageDataMapper.map(it) }
}
