package com.marcelokmats.jayakotlinissues.issuesList

import com.marcelokmats.jayakotlinissues.api.GitHubRetriever
import com.marcelokmats.jayakotlinissues.api.Issue
import com.marcelokmats.jayakotlinissues.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssuesPresenter(issuesView: IssuesView) : BasePresenter<IssuesView>(issuesView) {

    @Inject
    lateinit var gitHubRetriever: GitHubRetriever

    private val subs = CompositeDisposable()

    init {

        //val component = DaggerServiceComponent.builder()
            //.serviceModule(ServiceModule())
            //.build()

        //gitHubRetriever = component.githubRetriever()
    }

    override fun onViewCreated() {
        subs.add(
            gitHubRetriever.getKotlinIssues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnTerminate(view.sho)
                .subscribe(
                    { issues -> this.showIssues(issues ?: emptyList()) },
                    { view.showTimeoutError() }
                )
        )
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
    }

    private fun showIssues(issueList : List<Issue>) {
        if (issueList.isNotEmpty()) {
            view.showIssueList(issueList)
        } else {
            view.showTimeoutError()
        }
    }

}