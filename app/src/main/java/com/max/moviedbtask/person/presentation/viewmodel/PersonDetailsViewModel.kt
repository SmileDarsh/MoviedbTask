package com.max.moviedbtask.person.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.max.moviedbtask.core.network.errorInternet
import com.max.moviedbtask.core.network.errorResponse
import com.max.moviedbtask.core.platform.BaseViewModel
import com.max.moviedbtask.core.utils.io
import com.max.moviedbtask.core.utils.ui
import com.max.moviedbtask.person.domain.interactor.GetPersonDetailsInteractor
import com.max.moviedbtask.person.domain.interactor.GetPersonImageInteractor
import com.max.moviedbtask.person.presentation.viewmodel.state.PersonVS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class PersonDetailsViewModel(
    private val getPersonDetailsInteractor: GetPersonDetailsInteractor,
    private val getPersonImageInteractor: GetPersonImageInteractor
) : BaseViewModel() {

    val viewState: LiveData<PersonVS> get() = mViewState
    private val mViewState = MutableLiveData<PersonVS>()

    fun getPersonDetails(personId: Int) {
        viewModelScope.launch {
            try {
                io {
                    getPersonDetailsInteractor.execute(GetPersonDetailsInteractor.Params(personId))
                        .collect {
                            ui {
                                mViewState.value = PersonVS.AddPersonDetails(it)
                            }
                        }
                }
            } catch (e: Exception) {
                ui {
                    val response = e.errorResponse()
                    when {
                        response != null -> {
                            response.status_message?.let { mViewState.value = PersonVS.Error(it) }
                        }
                        e.errorInternet() -> {
                            mViewState.value = PersonVS.InternetError
                        }
                        else -> {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    fun getPersonImage(personId: Int) {
        viewModelScope.launch {
            try {
                io {
                    getPersonImageInteractor.execute(
                        GetPersonImageInteractor.Params(personId)
                    ).collect {
                        ui {
                            if (it == "0")
                                mViewState.value = PersonVS.Empty
                            else {
                                mViewState.value = PersonVS.AddPersonImage(it)
                            }

                        }
                    }
                }
            } catch (e: Exception) {
                ui {
                    e.printStackTrace()
                }
            }
        }
    }
}