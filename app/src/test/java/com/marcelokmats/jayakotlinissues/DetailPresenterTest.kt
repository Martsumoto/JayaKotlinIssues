package com.marcelokmats.jayakotlinissues

import com.marcelokmats.jayakotlinissues.api.GithubService
import com.marcelokmats.jayakotlinissues.api.IssueDetail
import com.marcelokmats.jayakotlinissues.api.User
import com.marcelokmats.jayakotlinissues.issueDetail.IssueDetailPresenter
import com.marcelokmats.jayakotlinissues.issueDetail.IssueDetailView
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
import java.util.*


class DetailPresenterTest {

    @Mock
    lateinit var githubApi: GithubService

    @Mock
    lateinit var view : IssueDetailView

    private lateinit var presenter : IssueDetailPresenter

    @Before
    fun setUp() {
        initMocks(this)

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

        presenter = IssueDetailPresenter(view)
        presenter.githubApi = githubApi
    }

    @Test
    fun `test call show issue detail`() {
        // given
        val issueDetail = IssueDetail("title", "state", "body", User("login", "avatarUrl"), "htmlUrl", Date())

        // when
        `when`(view.getIssueNumber()).thenReturn("123")
        `when`(githubApi.getKotlinIssueDetail("123")).thenReturn(Observable.just(issueDetail))
        presenter.onViewCreated()

        // then
        then(view).should().showIssueDetail(issueDetail)
    }

    @Test
    fun `test call show error due to retrofit error`() {
        // when
        `when`(view.getIssueNumber()).thenReturn("123")
        `when`(githubApi.getKotlinIssueDetail("123")).thenReturn(Observable.error(HttpException(buildResponse())))
        presenter.onViewCreated()

        // then
        then(view).should().showLoadError(R.string.issue_detail_error)
    }

    @Test
    fun `test call show error due to issue number not set`() {
        // when
        presenter.onViewCreated()

        // then
        then(view).should().showLoadError(R.string.issue_detail_number_error)
    }

    @Test
    fun `test github api issue detail fetch`() {
        val issueDetail = IssueDetail("title", "state", "body", User("login", "avatarUrl"), "htmlUrl", Date())

        `when`(githubApi.getKotlinIssueDetail("number")).thenReturn(Observable.just(issueDetail))
        val testObserver = TestObserver<IssueDetail>()

        val result = githubApi.getKotlinIssueDetail("number")

        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val retrieverResult = testObserver.values()[0]
        Assert.assertThat(retrieverResult.body, CoreMatchers.`is`("body"))
    }
}