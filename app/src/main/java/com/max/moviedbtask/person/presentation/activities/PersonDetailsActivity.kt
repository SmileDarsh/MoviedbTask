package com.max.moviedbtask.person.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.max.moviedbtask.R
import com.max.moviedbtask.core.utils.listToString
import com.max.moviedbtask.core.utils.loadUrl
import com.max.moviedbtask.person.domain.model.Person
import com.max.moviedbtask.person.domain.model.PersonDetails
import com.max.moviedbtask.person.presentation.adapters.PersonImageAdapter
import com.max.moviedbtask.person.presentation.viewmodel.PersonDetailsViewModel
import com.max.moviedbtask.person.presentation.viewmodel.state.PersonVS
import kotlinx.android.synthetic.main.activity_person_details.*
import kotlinx.android.synthetic.main.item_biography.*
import kotlinx.android.synthetic.main.item_person.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class PersonDetailsActivity : AppCompatActivity() {
    private val mViewModel: PersonDetailsViewModel by viewModel()
    private val mAdapter = PersonImageAdapter()
    private lateinit var mPerson: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        mPerson = intent.getParcelableExtra("person") as Person

        personImagesRecyclerView()

        mViewModel.viewState.observe(this@PersonDetailsActivity, Observer {
            when (it) {
                is PersonVS.AddPersonDetails -> {
                    localData(it.personDetails)
                    cpProgress.hide()
                }
                is PersonVS.AddPersonImage -> {
                    mAdapter.addPersonImage(it.image)
                }
                is PersonVS.InternetError -> {
                    cpProgress.noInternetFound {
                        mViewModel.getPersonDetails(mPerson.id)
                        mViewModel.getPersonImage(mPerson.id)
                    }
                }
                is PersonVS.Error -> {
                }
            }
        })

        mViewModel.getPersonDetails(mPerson.id)
        mViewModel.getPersonImage(mPerson.id)
    }

    private fun localData(personDetails: PersonDetails) {
        vPerson.animation =
            AnimationUtils.loadAnimation(this, R.anim.fade_transition_animation_right)
        vBiography.animation =
            AnimationUtils.loadAnimation(this, R.anim.fade_transition_animation_left)
        tvName.text = mPerson.name
        tvKnownForDepartment.text = mPerson.knownForDepartment
        tvKnownFor.listToString(mPerson.knownFor.map { it.title })
        ivImage.loadUrl(mPerson.image)
        tvBiography.text = personDetails.biography
        tvBorn.text = personDetails.birthday

        tvPlaceOfBirth.text = personDetails.placeOfBirth

        if (personDetails.placeOfBirth.isEmpty())
            tvIn.visibility = View.INVISIBLE

        if (personDetails.deathday.isNotEmpty()) {
            tvDiedTitle.visibility = View.VISIBLE
            tvDied.visibility = View.VISIBLE
            tvDied.text = personDetails.deathday
        }
    }

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    private fun personImagesRecyclerView() {
        rvPersonImages.animation = AnimationUtils.loadAnimation(this, R.anim.fade_scale_animation)
        rvPersonImages?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = mAdapter
            isNestedScrollingEnabled = false
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun personImageEvent(image: ImageView) {
        val options: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@PersonDetailsActivity,
                image,
                getString(R.string.person_image)
            )
        startActivity(
            Intent(this@PersonDetailsActivity, PersonImageActivity::class.java),
            options.toBundle()
        )
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}