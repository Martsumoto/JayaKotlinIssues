package com.marcelokmats.jayakotlinissues.issueDetail

import com.marcelokmats.jayakotlinissues.R
import com.marcelokmats.jayakotlinissues.api.GithubService
import com.marcelokmats.jayakotlinissues.api.IssueDetail
import com.marcelokmats.jayakotlinissues.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssueDetailPresenter(issueDetailView: IssueDetailView) : BasePresenter<IssueDetailView>(issueDetailView) {

    @Inject
    lateinit var githupApi: GithubService

    private val subs = CompositeDisposable()

    var issueNumber : String? = null

    init {

        //val component = DaggerServiceComponent.builder()
            //.serviceModule(ServiceModule())
            //.build()

        //gitHubRetriever = component.githubRetriever()
    }

    override fun onViewCreated() {
        issueNumber?.let { fetchIssueDetial(it) }
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
        subs.clear()
    }

    private fun fetchIssueDetial(number : String) {
        view.showProgressBar()
        subs.add(githupApi.getKotlinIssueDetail(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { issueDetail -> issueDetail.let {  this.showIssueDetail(it) }},
                { view.showLoadError(R.string.issue_detail_error) }
            )
        )
    }

    private fun showIssueDetail(issueDetail : IssueDetail) {
        view.showIssueDetail(issueDetail)
    }

}