package com.example.kit.githubclient.gitService

import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GitHubServiceManager {
    companion object{
        private val BASE_URI="https://api.github.com/"
    }

    val gituserService by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GitUserService::class.java)
    }

    fun getUserData(name: String)
    {
        gituserService.getData(name).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { gotUserData(it) }, { noUserData(it) })
    }

    private fun gotUserData(data : GitUserWrapper) {
        //todo bind to adapter
    }
    private fun noUserData(t : Throwable) {
        //todo throw error
    }

}
