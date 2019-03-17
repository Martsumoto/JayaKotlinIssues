package com.marcelokmats.jayakotlinissues.di.module

import android.app.Application
import android.content.Context
import com.marcelokmats.jayakotlinissues.base.BaseView
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
object ContextModule {
    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView) = baseView.getContext()

    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context) = context.applicationContext as Application
}