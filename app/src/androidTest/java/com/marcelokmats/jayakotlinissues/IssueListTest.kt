package com.marcelokmats.jayakotlinissues

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.ActivityTestRule
import com.marcelokmats.jayakotlinissues.issuesList.IssuesActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class IssueListTest {
    @get:Rule
    val rule = ActivityTestRule(IssuesActivity::class.java)

    @get:Rule
    val intentsTestRule = IntentsTestRule(IssuesActivity::class.java)

    private lateinit var robot: IssueListRobot

    val activity: IssuesActivity by lazy {
        rule.activity
    }

    @Before
    fun setup() {
        robot = IssueListRobot(activity)
    }

    @Test
    fun progressBarDisplayed() {
        robot.progressBarShown()
    }

    @Test
    fun listDisplayed() {
        robot.listShown()
    }

    @Test
    fun displayIssueDetail() {
        Intents.init()
        robot.clickListItem().showIssueDetail()
        Intents.release()
    }
}