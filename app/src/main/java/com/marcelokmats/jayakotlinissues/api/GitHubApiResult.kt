package com.marcelokmats.jayakotlinissues.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class Issue(val number: String?,
                      val title: String?,
                      val state: String?,
                      val user: User?)

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
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(avatar_url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}