package com.marcelokmats.jayakotlinissues.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/repos/{user}/{repo}/issues")
    fun getIssues(
        @Path("user") user : String,
        @Path("repo") repo : String) : Observable<List<Issue>>

    @GET("/repos/{user}/{repo}/issues/{id}")
    fun getIssueDetail(
        @Path("user") user : String,
        @Path("repo") repo : String,
        @Path("id") id : String) : Observable<List<Issue>>
}