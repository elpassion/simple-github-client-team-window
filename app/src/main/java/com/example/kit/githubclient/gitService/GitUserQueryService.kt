package com.example.kit.githubclient.gitService

import com.example.kit.githubclient.dataModels.User
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface GitUserQueryService {
    @GET("search/users")
    fun getData (@Query("q") name : String) : Observable<List<User>>
}
