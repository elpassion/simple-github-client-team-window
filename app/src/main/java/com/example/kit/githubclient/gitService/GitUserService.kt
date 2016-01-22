package com.example.kit.githubclient.gitService

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface GitUserService {
    @GET("user/name")
    fun getData (@Path("name") name : String) : Observable<GitUserWrapper>
}

