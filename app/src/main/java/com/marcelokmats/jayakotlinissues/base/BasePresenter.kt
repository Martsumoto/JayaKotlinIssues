package com.marcelokmats.jayakotlinissues.base

import com.marcelokmats.jayakotlinissues.di.component.DaggerPresenterInjector
import com.marcelokmats.jayakotlinissues.di.component.PresenterInjector
import com.marcelokmats.jayakotlinissues.di.module.ContextModule
import com.marcelokmats.jayakotlinissues.di.module.NetworkModule
import com.marcelokmats.jayakotlinissues.issueDetail.IssueDetailPresenter
import com.marcelokmats.jayakotlinissues.issuesList.IssuesPresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    private val issuesInjector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is IssuesPresenter -> issuesInjector.inject(this)
            is IssueDetailPresenter -> issuesInjector.inject(this)
        }
    }
}