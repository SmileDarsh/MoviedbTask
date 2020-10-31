package com.max.moviedbtask.person.domain.interactor

import com.max.moviedbtask.core.interactor.Interactor
import com.max.moviedbtask.person.domain.model.Person
import com.max.moviedbtask.person.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class GetPersonsInteractor(private val personRepository: PersonRepository) :
    Interactor<GetPersonsInteractor.Params, Flow<Person>> {
    override fun execute(params: Params): Flow<Person> =
        personRepository.getPopularPersons(params.page)

    data class Params(val page: Int)
}