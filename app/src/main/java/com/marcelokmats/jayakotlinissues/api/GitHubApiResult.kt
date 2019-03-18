package com.marcelokmats.jayakotlinissues.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class Issue(val number: String?,
                      val title: String?,
                      val state: String?)

data class IssueDetail(val title: String?,
                       val state: String?,
                       val body: String?,
                       val user: User?,
                       @SerializedName("html_url") val htmlUrl: String?,
                       @SerializedName("created_at") val createDate: Date?
)

data class User(
    val login: String?,
    val avatar_url: String?
)