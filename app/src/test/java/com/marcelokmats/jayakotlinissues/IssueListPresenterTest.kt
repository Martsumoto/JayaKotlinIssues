package com.marcelokmats.jayakotlinissues

import com.marcelokmats.jayakotlinissues.api.GithubService
import com.marcelokmats.jayakotlinissues.api.Issue
import com.marcelokmats.jayakotlinissues.issuesList.IssuesPresenter
import com.marcelokmats.jayakotlinissues.issuesList.IssuesView
import com.marcelokmats.jayakotlinissues.util.buildResponse
import com.marcelokmats.jayakotlinissues.util.immediate
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.HttpException


class IssueListPresenterTest {

    @Mock
    lateinit var githubApi: GithubService

    @Mock
    lateinit var issuesView : IssuesView

    private lateinit var issuesPresenter : IssuesPresenter

    @Before
    fun setUp() {
        initMocks(this)

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

        issuesPresenter = IssuesPresenter(issuesView)
        issuesPresenter.githubApi = githubApi
    }

    @Test
    fun `test call show issue list`() {
        // given
        val issueList = listOf(
            Issue("1", "title1", "state1"),
            Issue("2", "title2", "state2"),
            Issue("3", "title3", "state3")
        )

        // when
        `when`(githubApi.getKotlinIssues()).thenReturn(Observable.just(issueList))
        issuesPresenter.onViewCreated()

        // then
        then(issuesView).should().showIssueList(issueList)
    }

    @Test
    fun `test call show empty`() {
        // given
        val issueList = emptyList<Issue>()

        // when
        `when`(githubApi.getKotlinIssues()).thenReturn(Observable.just(issueList))
        issuesPresenter.onViewCreated()

        // then
        then(issuesView).should().showLoadError(R.string.empty_list)
    }

    @Test
    fun `test call show error`() {
        // when
        `when`(githubApi.getKotlinIssues()).thenReturn(Observable.error(HttpException(buildResponse())))
        issuesPresenter.onViewCreated()

        // then
        then(issuesView).should().showLoadError(R.string.load_issues_error)
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
        Assert.assertThat(retrieverResult.size, CoreMatchers.`is`(3))
        Assert.assertThat(retrieverResult[0].title, CoreMatchers.`is`("title1"))
        Assert.assertThat(retrieverResult[1].title, CoreMatchers.`is`("title2"))
        Assert.assertThat(retrieverResult[2].title, CoreMatchers.`is`("title3"))
        Assert.assertThat(retrieverResult[0].state, CoreMatchers.`is`("state1"))
        Assert.assertThat(retrieverResult[1].state, CoreMatchers.`is`("state2"))
        Assert.assertThat(retrieverResult[2].state, CoreMatchers.`is`("state3"))
    }
}