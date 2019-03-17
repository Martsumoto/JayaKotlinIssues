package com.marcelokmats.jayakotlinissues.di.component

import com.marcelokmats.jayakotlinissues.base.BaseView
import com.marcelokmats.jayakotlinissues.di.module.ContextModule
import com.marcelokmats.jayakotlinissues.di.module.NetworkModule
import com.marcelokmats.jayakotlinissues.issueDetail.IssueDetailPresenter
import com.marcelokmats.jayakotlinissues.issuesList.IssuesPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    NetworkModule::class
])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(issuesPresenter: IssuesPresenter)

    fun inject(issueDetailPresenter: IssueDetailPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}
