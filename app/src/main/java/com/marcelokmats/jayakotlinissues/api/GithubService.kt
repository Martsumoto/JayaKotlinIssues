package com.marcelokmats.jayakotlinissues.api

import com.marcelokmats.jayakotlinissues.util.JET_BRAINS
import com.marcelokmats.jayakotlinissues.util.KOTLIN
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/repos/{user}/{repo}/issues")
    fun getIssues(
        @Path("user") user : String,
        @Path("repo") repo : String) : Observable<List<Issue>>

    @GET("/repos/$JET_BRAINS/$KOTLIN/issues")
    fun getKotlinIssues() : Observable<List<Issue>>

    @GET("/repos/{user}/{repo}/issues/{number}")
    fun getIssueDetail(
        @Path("user") user : String,
        @Path("repo") repo : String,
        @Path("number") number : String) : Observable<IssueDetail>

    @GET("/repos/$JET_BRAINS/$KOTLIN/issues/{number}")
    fun getKotlinIssueDetail(
        @Path("number") number : String) : Observable<IssueDetail>
}