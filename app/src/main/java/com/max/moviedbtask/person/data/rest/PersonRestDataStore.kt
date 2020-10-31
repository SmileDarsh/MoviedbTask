package com.max.moviedbtask.person.data.rest

import com.max.moviedbtask.person.data.rest.response.PersonDetailsResponse
import com.max.moviedbtask.person.data.rest.response.PersonImageResponse
import com.max.moviedbtask.person.data.rest.response.PersonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PersonRestDataStore {
    fun getPopularPersons(page: Int): Flow<PersonResponse> = flow {
        val persons = PersonApiClient.getApiClient().getPopularPersons(page).results

        if (persons.isEmpty()) emit(PersonResponse(0))

        persons.forEach { emit(it) }
    }

    fun getPersonDetails(personId: Int): Flow<PersonDetailsResponse> = flow {
        emit(PersonApiClient.getApiClient().getPersonDetails(personId))
    }

    fun getPersonImages(personId: Int): Flow<PersonImageResponse> = flow {
        val images = PersonApiClient.getApiClient().getPersonImages(personId).profiles

        if (images.isEmpty()) emit(PersonImageResponse("0"))

        images.forEach { emit(it) }
    }
}