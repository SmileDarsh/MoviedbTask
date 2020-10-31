package com.max.moviedbtask.person.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.max.moviedbtask.core.network.errorInternet
import com.max.moviedbtask.core.network.errorResponse
import com.max.moviedbtask.core.platform.BaseViewModel
import com.max.moviedbtask.core.utils.io
import com.max.moviedbtask.core.utils.ui
import com.max.moviedbtask.person.domain.interactor.GetPersonsInteractor
import com.max.moviedbtask.person.presentation.viewmodel.state.PersonVS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class PersonsViewModel(private val getPersonsInteractor: GetPersonsInteractor) :
    BaseViewModel() {

    val viewState: LiveData<PersonVS> get() = mViewState
    private val mViewState = MutableLiveData<PersonVS>()

    fun getPersons(page: Int) {
        viewModelScope.launch {
            try {
                io {
                    getPersonsInteractor.execute(
                        GetPersonsInteractor.Params(page)
                    ).collect {
                        ui {
                            if (it.id == 0)
                                mViewState.value = PersonVS.Empty
                            else {
                                mViewState.value = PersonVS.AddPerson(it)
                            }

                        }
                    }
                }
            } catch (e: Exception) {
                ui {
                    val response = e.errorResponse()
                    when {
                        response != null -> {
                            response.status_message?.let { mViewState.value = PersonVS.Error(it) }
                            response.errors?.let { mViewState.value = PersonVS.Error(it[0]) }
                        }
                        e.errorInternet() -> {
                            mViewState.value = PersonVS.InternetError
                        }
                        else -> {
                            mViewState.value = PersonVS.Error(e.message)
                        }
                    }
                }
            }
        }
    }
}