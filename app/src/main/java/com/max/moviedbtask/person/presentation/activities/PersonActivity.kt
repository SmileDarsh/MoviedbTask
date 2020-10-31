package com.max.moviedbtask.person.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.max.moviedbtask.R
import com.max.moviedbtask.core.utils.PaginationListener
import com.max.moviedbtask.person.domain.model.Person
import com.max.moviedbtask.person.presentation.adapters.PersonAdapter
import com.max.moviedbtask.person.presentation.viewmodel.PersonsViewModel
import com.max.moviedbtask.person.presentation.viewmodel.state.PersonVS
import kotlinx.android.synthetic.main.activity_person.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonActivity : AppCompatActivity() {
    private val mViewModel: PersonsViewModel by viewModel()
    private val mAdapter = PersonAdapter()
    private var mIsLoader = false
    private var mIsLastPage = false
    private var mPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        personsRecyclerView()

        mViewModel.viewState.observe(this@PersonActivity, Observer {
            when (it) {
                is PersonVS.AddPerson -> {
                    mAdapter.addPerson(it.person)
                    mIsLoader = false
                    cpProgress.hide()
                }
                is PersonVS.Empty -> {
                    stopLoader()
                }
                is PersonVS.InternetError -> {
                    cpProgress.noInternetFound { mViewModel.getPersons(mPage) }
                }
                is PersonVS.Error -> {
                    stopLoader()
                }
            }
        })

        mViewModel.getPersons(mPage)
    }

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    private fun personsRecyclerView() {
        val mLayoutManager = LinearLayoutManager(this@PersonActivity)
        rvPersons?.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter

            addOnScrollListener(object : PaginationListener(mLayoutManager) {
                override fun loadMoreItems() {
                    mIsLoader = true
                    mAdapter.isLoaderVisible(mIsLoader)
                    mPage++
                    mViewModel.getPersons(mPage)
                }

                override fun isLastPage(): Boolean = mIsLastPage

                override fun isLoading(): Boolean = mIsLoader

            })
        }
    }

    private fun stopLoader(){
        mIsLoader = false
        mIsLastPage = true
        mAdapter.isLoaderVisible(mIsLoader)
        mAdapter.notifyDataSetChanged()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun personEvent(item: Person) {
        startActivity(
            Intent(this@PersonActivity, PersonDetailsActivity::class.java)
                .putExtra("person", item)
        )
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}