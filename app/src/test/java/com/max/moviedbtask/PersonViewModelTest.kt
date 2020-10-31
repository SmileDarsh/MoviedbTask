package com.max.moviedbtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.max.moviedbtask.core.utils.FakePersonList
import com.max.moviedbtask.person.domain.interactor.GetPersonsInteractor
import com.max.moviedbtask.person.presentation.viewmodel.PersonsViewModel
import com.max.moviedbtask.person.presentation.viewmodel.state.PersonVS
import com.max.moviedbtask.utils.CoroutinesRule
import com.max.moviedbtask.utils.getOrAwaitValue
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import org.junit.*

class PersonViewModelTest {
    private var mPage = 1

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = CoroutinesRule()

    private val useCaseMock = mockk<GetPersonsInteractor>()
    private lateinit var viewModel: PersonsViewModel
    private val observer = mockk<Observer<PersonVS>>(relaxed = true)

    @Before
    fun setup() {
        viewModel = PersonsViewModel(useCaseMock)
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun `retrieve posts successful`() {
        coEvery { useCaseMock.execute(GetPersonsInteractor.Params(mPage)) } returns FakePersonList.dataList.asFlow()
        viewModel.getPersons(mPage)
        viewModel.viewState.getOrAwaitValue()
        verify(exactly = 4) { observer.onChanged(any<PersonVS.AddPerson>()) }
    }

    @Test
    fun `retrieve posts failure`() {
        coEvery { useCaseMock.execute(GetPersonsInteractor.Params(mPage)) } throws UnsupportedOperationException()
        viewModel.getPersons(mPage)
        viewModel.viewState.getOrAwaitValue()
        verify { observer.onChanged(any<PersonVS.Error>()) }
    }

    @Suppress("UNREACHABLE_CODE")
    @Test(expected = UnsupportedOperationException::class)
    fun `retrieve posts with exception`() {
        coEvery { useCaseMock.execute(GetPersonsInteractor.Params(mPage)) } returns throw UnsupportedOperationException()
        viewModel.getPersons(mPage)
    }
}
