package com.marcelokmats.jayakotlinissues.api

import com.marcelokmats.jayakotlinissues.util.BASE_URL
import com.marcelokmats.jayakotlinissues.util.JET_BRAINS
import com.marcelokmats.jayakotlinissues.util.KOTLIN
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GitHubRetriever {
    private val service: GithubService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        service = retrofit.create(GithubService::class.java)
    }

    fun getKotlinIssues() = service.getIssues(JET_BRAINS, KOTLIN)

    fun getKotlinIssueDetail(number : String) = service.getIssueDetail(JET_BRAINS, KOTLIN, number)
}