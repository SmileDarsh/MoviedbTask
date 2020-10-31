package com.max.moviedbtask.di

import com.max.moviedbtask.person.data.repository.PersonRepositoryImpl
import com.max.moviedbtask.person.data.rest.PersonRestDataStore
import com.max.moviedbtask.person.domain.interactor.GetPersonDetailsInteractor
import com.max.moviedbtask.person.domain.interactor.GetPersonImageInteractor
import com.max.moviedbtask.person.domain.interactor.GetPersonsInteractor
import com.max.moviedbtask.person.domain.repository.PersonRepository
import com.max.moviedbtask.person.presentation.viewmodel.PersonDetailsViewModel
import com.max.moviedbtask.person.presentation.viewmodel.PersonsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val personModule = module {
    viewModel { PersonDetailsViewModel(get(), get()) }
    viewModel { PersonsViewModel(get()) }
    single { GetPersonImageInteractor(get()) }
    single { GetPersonDetailsInteractor(get()) }
    single { GetPersonsInteractor(get()) }
    single<PersonRepository> { PersonRepositoryImpl(get()) }
    single { PersonRestDataStore() }
}

val koinModules = listOf(personModule)