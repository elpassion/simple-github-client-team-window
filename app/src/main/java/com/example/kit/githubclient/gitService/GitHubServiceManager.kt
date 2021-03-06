package com.example.kit.githubclient.gitService

import android.app.Activity
import com.example.kit.githubclient.MainActivity
import com.example.kit.githubclient.UserDetailsActivity
import com.example.kit.githubclient.dataModels.ItemModel
import com.example.kit.githubclient.dataModels.Repository
import com.example.kit.githubclient.dataModels.User
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

var gitUserService = GitHubServiceManager.retrofit.create(GitUsersService::class.java)
var gitReposService = GitHubServiceManager.retrofit.create(GitReposService::class.java)
var gitUserDataService = GitHubServiceManager.retrofit.create(GitUserDataService::class.java)

private val gitUserQuerryService by lazy {
    GitHubServiceManager.retrofit.create(GitUserQueryService::class.java)
}
class GitHubServiceManager(val activity : Activity) {
    companion object{
        private val BASE_URI="https://api.github.com"
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    public fun findUser(name: String) {
        gitUserQuerryService.getData(name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ dataLoaded(it) }, { dataNotLoaded(it) })
    }

    public fun getReposForUser(name :String) {
        gitUserDataService.getData(name).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe( {userReposLoaded(it)}, {dataNotLoaded(it)})
    }

    private fun userReposLoaded(list: List<Repository>) {
        (activity as UserDetailsActivity).loadRecyclerView(list)
    }

    public fun getMainList() {
        val users = gitUserService.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val repos =  gitReposService.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        rx.Observable.zip(users, repos, zipFun).subscribe( {dataLoaded(it)}, {dataNotLoaded(it)} )
    }

    private val zipFun = {
        users : List<User>, repos : List<Repository> ->
        (users+repos).sorted()
    }

    private fun dataLoaded(list : List<ItemModel>)
    {
        (activity as MainActivity).loadUsersAndReposList(list)
    }

    private fun dataNotLoaded(t : Throwable) {
        (activity as MainActivity).printMessege("There was a problem loading list")
    }

}
