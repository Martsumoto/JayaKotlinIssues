package com.marcelokmats.jayakotlinissues.issuesList

import com.marcelokmats.jayakotlinissues.R
import com.marcelokmats.jayakotlinissues.api.GithubService
import com.marcelokmats.jayakotlinissues.api.Issue
import com.marcelokmats.jayakotlinissues.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssuesPresenter(issuesView: IssuesView) : BasePresenter<IssuesView>(issuesView) {

    @Inject
    lateinit var githupApi: GithubService

    private val subs = CompositeDisposable()

    init {

        //val component = DaggerServiceComponent.builder()
            //.serviceModule(ServiceModule())
            //.build()

        //gitHubRetriever = component.githubRetriever()
    }

    override fun onViewCreated() {
        fetchKotlinIssues()
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
        subs.clear()
    }

    private fun showIssues(issueList : List<Issue>) {
        if (issueList.isNotEmpty()) {
            view.showIssueList(issueList)
        } else {
            view.showLoadError(R.string.empty_list)
        }
    }

    private fun fetchKotlinIssues() {
        view.showProgressBar()
        subs.add(
            githupApi.getKotlinIssues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnTerminate(view.sho)
                .subscribe(
                    { issues -> this.showIssues(issues ?: emptyList()) },
                    { view.showLoadError(R.string.load_issues_error) }
                )
        )
    }
}