package com.marcelokmats.jayakotlinissues.base

import android.content.Context

interface BaseView {

    fun getContext() : Context

    fun showProgressBar()

    fun showTimeoutError()

}