package com.max.moviedbtask.person.presentation.viewmodel.state

import com.max.moviedbtask.person.domain.model.Person
import com.max.moviedbtask.person.domain.model.PersonDetails

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
sealed class PersonVS {
    class AddPerson(val person: Person) : PersonVS()
    class AddPersonDetails(val personDetails: PersonDetails) : PersonVS()
    class AddPersonImage(val image: String) : PersonVS()
    class Error(val message: String?) : PersonVS()
    object InternetError : PersonVS()
    object Empty : PersonVS()
}