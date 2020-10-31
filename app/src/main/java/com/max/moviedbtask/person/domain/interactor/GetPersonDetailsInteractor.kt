package com.max.moviedbtask.person.domain.interactor

import com.max.moviedbtask.core.interactor.Interactor
import com.max.moviedbtask.person.domain.model.PersonDetails
import com.max.moviedbtask.person.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class GetPersonDetailsInteractor(private val personRepository: PersonRepository) :
    Interactor<GetPersonDetailsInteractor.Params, Flow<PersonDetails>> {
    override fun execute(params: Params): Flow<PersonDetails> =
        personRepository.getPersonDetails(params.personId)

    data class Params(val personId: Int)
}