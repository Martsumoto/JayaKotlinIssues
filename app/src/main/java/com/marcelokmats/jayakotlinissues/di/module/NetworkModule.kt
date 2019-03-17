package com.marcelokmats.jayakotlinissues.di.module

import com.marcelokmats.jayakotlinissues.api.GitHubRetriever
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGitHubRetriever() = GitHubRetriever()

}