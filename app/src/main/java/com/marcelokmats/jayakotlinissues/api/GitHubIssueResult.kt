package com.marcelokmats.jayakotlinissues.api

import com.google.gson.annotations.SerializedName

data class GitHubIssueResult(
    @SerializedName(value = "items")
    val issues: List<Issue>
)

data class Issue(val id: Long?,
                      val title: String?,
                      val state: String?,
                      val owner: Owner?)

data class Owner(
    val id: Long?,
    val login: String?,
    val avatar_url: String?
)