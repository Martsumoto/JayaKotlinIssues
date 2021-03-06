package com.marcelokmats.jayakotlinissues.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import com.marcelokmats.jayakotlinissues.api.GitHubRetriever
import com.marcelokmats.jayakotlinissues.api.GithubService
import com.marcelokmats.jayakotlinissues.util.BASE_URL
import com.marcelokmats.jayakotlinissues.util.GITHUB_DATE_FORMAT
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGitHubRetriever() = GitHubRetriever()

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCache(application: Application) : Cache {
        val cacheSize = (1024 * 1024 * 10).toLong() // 10MB
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setDateFormat(GITHUB_DATE_FORMAT)
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGithubApi(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}