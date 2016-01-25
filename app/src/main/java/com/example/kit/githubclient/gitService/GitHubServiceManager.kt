package com.example.kit.githubclient.gitService

import com.example.kit.githubclient.dataModels.ItemModel
import com.example.kit.githubclient.dataModels.Repository
import com.example.kit.githubclient.dataModels.User
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class GitHubServiceManager {
    companion object{
        private val BASE_URI="https://api.github.com"
        private val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
    val gitUserService by lazy {
        retrofit.create(GitUsersService::class.java)
    }
    val gitReposService by lazy {
        retrofit.create(GitReposService::class.java)
    }
    val gitUserDataService by lazy {
        retrofit.create(GitUserDataService::class.java)
    }

    public fun getMainList() {
        val users = gitUserService.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val repos =  gitReposService.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        rx.Observable.zip(users, repos, zipFun).subscribe( {dataLoaded(it)}, {dataNotLoaded(it)} )
    }

    val zipFun = {
        users : List<User>, repos : List<Repository> ->
        val argList = ArrayList<ItemModel>()
        //todo populate
        argList
    }

    private fun dataLoaded(list : ArrayList<ItemModel>?)
    {
        //todo bind to adapter
        //Log.e()
    }

    private fun dataNotLoaded(t : Throwable) {
        throw t
    }

}
