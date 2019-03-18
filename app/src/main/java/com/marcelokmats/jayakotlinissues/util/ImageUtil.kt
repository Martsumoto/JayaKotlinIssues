package com.marcelokmats.jayakotlinissues.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtil {

    fun setupImage(context: Context, imageUrl: String, imageHolder: ImageView) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(createProgressDrawable(context))
        requestOptions.error(android.R.drawable.stat_notify_error)

        Glide.with(context).setDefaultRequestOptions(requestOptions).load(imageUrl).into(imageHolder)

    }

    fun createProgressDrawable(context: Context) : CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}