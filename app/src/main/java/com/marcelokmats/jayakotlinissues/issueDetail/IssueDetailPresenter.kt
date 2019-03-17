package com.marcelokmats.jayakotlinissues.issueDetail

import com.marcelokmats.jayakotlinissues.api.GitHubRetriever
import com.marcelokmats.jayakotlinissues.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class IssueDetailPresenter(issueDetailView: IssueDetailView) : BasePresenter<IssueDetailView>(issueDetailView) {

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
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
    }

}