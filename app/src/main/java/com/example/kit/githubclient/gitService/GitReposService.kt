package com.example.kit.githubclient.gitService

import com.example.kit.githubclient.dataModels.Repository
import retrofit2.http.GET
import rx.Observable


interface GitReposService {
    @GET("repositories")
    fun getData () : Observable<List<Repository>>
}

