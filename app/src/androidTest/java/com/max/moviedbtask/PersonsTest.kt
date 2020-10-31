package com.max.moviedbtask

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.max.moviedbtask.core.utils.FakePersonList
import com.max.moviedbtask.person.presentation.activities.PersonActivity
import com.max.moviedbtask.person.presentation.adapters.PersonAdapter.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/***
 *  before run this code you want first use [FakePersonList] to test your recyclerView and this actions
 * */
@RunWith(AndroidJUnit4::class)
@LargeTest
class PersonsTest {
    private val mItemInList = 3
    private val mPersonListTest = FakePersonList.dataList[mItemInList]

    @get:Rule
    var activityRule: ActivityScenarioRule<PersonActivity> =
        ActivityScenarioRule(PersonActivity::class.java)

    @Test
    fun initListVisible() {
        onView(withId(R.id.rvPersons)).check(matches(isDisplayed()))
    }

    @Test
    fun selectTestItem() {
        onView(withId(R.id.rvPersons)).perform(
            actionOnItemAtPosition<PersonViewHolder>(
                mItemInList,
                click()
            )
        )
        onView(withId(R.id.tvKnownForDepartment)).check(matches(withText(mPersonListTest.knownForDepartment)))
    }

    @Test
    fun backToTestItems() {
        onView(withId(R.id.rvPersons)).perform(
            actionOnItemAtPosition<PersonViewHolder>(
                mItemInList,
                click()
            )
        )
        onView(withId(R.id.tvKnownForDepartment)).check(matches(withText(mPersonListTest.knownForDepartment)))
        pressBack()
        onView(withId(R.id.rvPersons)).check(matches(isDisplayed()))
    }
}