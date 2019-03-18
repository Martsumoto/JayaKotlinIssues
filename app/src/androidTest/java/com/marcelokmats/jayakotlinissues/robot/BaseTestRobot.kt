package com.marcelokmats.jayakotlinissues.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId

open class BaseTestRobot {
    fun fillEditText(resId: Int, text: String) =
        onView(withId(resId)).perform(replaceText(text), closeSoftKeyboard())

    fun doneEditText(resId: Int) = onView((withId(resId))).perform(pressImeActionButton())
}
