package com.example.kit.githubclient.gitService

import com.example.kit.githubclient.dataModels.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface GitUserDataService {
    @GET("users/{name}/repos")
    fun getData(@Path("name") name: String): Observable<List<Repository>>
}

