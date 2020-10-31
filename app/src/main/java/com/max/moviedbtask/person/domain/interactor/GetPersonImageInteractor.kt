package com.max.moviedbtask.person.domain.interactor

import com.max.moviedbtask.core.interactor.Interactor
import com.max.moviedbtask.person.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class GetPersonImageInteractor(private val personRepository: PersonRepository) :
    Interactor<GetPersonImageInteractor.Params, Flow<String>> {
    override fun execute(params: Params): Flow<String> =
        personRepository.getPersonImages(params.personId)

    data class Params(val personId: Int)
}