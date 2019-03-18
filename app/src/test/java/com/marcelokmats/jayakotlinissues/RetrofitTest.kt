package com.marcelokmats.jayakotlinissues

import com.marcelokmats.jayakotlinissues.api.GithubService
import com.marcelokmats.jayakotlinissues.api.Issue
import com.marcelokmats.jayakotlinissues.api.IssueDetail
import com.marcelokmats.jayakotlinissues.api.User
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks


class RetrofitTest {

    @Mock
    lateinit var githubApi: GithubService

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun `test github api issue detail fetch`() {
        val issueDetail = IssueDetail("title", "state", "body", User("login", "avatarUrl"), "htmlUrl", "creationDate")

        `when`(githubApi.getKotlinIssueDetail("number")).thenReturn(Observable.just(issueDetail))
        val testObserver = TestObserver<IssueDetail>()

        val result = githubApi.getKotlinIssueDetail("number")

        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val retrieverResult = testObserver.values()[0]
        assertThat(retrieverResult.body, `is`("body"))
    }

    @Test
    fun `test github api fetching issues list`() {
        val issueList = listOf(
            Issue("1", "title1", "state1"),
            Issue("2", "title2", "state2"),
            Issue("3", "title3", "state3")
        )

        `when`(githubApi.getKotlinIssues()).thenReturn(Observable.just(issueList))
        val testObserver = TestObserver<List<Issue>>()

        val result = githubApi.getKotlinIssues()

        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val retrieverResult = testObserver.values()[0]
        assertThat(retrieverResult.size, `is`(3))
        assertThat(retrieverResult[0].title, `is`("title1"))
        assertThat(retrieverResult[1].title, `is`("title2"))
        assertThat(retrieverResult[2].title, `is`("title3"))
        assertThat(retrieverResult[0].state, `is`("state1"))
        assertThat(retrieverResult[1].state, `is`("state2"))
        assertThat(retrieverResult[2].state, `is`("state3"))
    }
}