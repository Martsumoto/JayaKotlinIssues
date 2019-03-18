package com.marcelokmats.jayakotlinissues

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.marcelokmats.jayakotlinissues.api.IssueDetail
import com.marcelokmats.jayakotlinissues.robot.BaseTestRobot

class IssueListRobot(private val context : Context) : BaseTestRobot() {

    fun progressBarShown() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    fun errorShown() {
        onView(withId(R.id.tvErrorMessage)).check(matches(isDisplayed()))
    }

    fun listShown() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    fun  clickListItem() = apply {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    fun showIssueDetail() {
        intended(hasComponent(IssueDetail::class.java.name))
    }
}