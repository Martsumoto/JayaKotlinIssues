package com.marcelokmats.jayakotlinissues.api

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GitHubRetriever {
    private val service: GithubService

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val JET_BRAINS = "JetBrains"
        const val KOTLIN = "kotlin"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        service = retrofit.create(GithubService::class.java)
    }

    fun getKotlinIssues() = service.getIssues(JET_BRAINS, KOTLIN)

    fun getKotlinIssueDetail(id : String) = service.getIssueDetail(JET_BRAINS, KOTLIN, id)
}