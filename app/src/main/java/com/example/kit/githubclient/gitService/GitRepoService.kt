package com.example.kit.githubclient.gitService

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface GitRepoService {
    @GET("TODO")
    fun getData (@Path("TODO") path : String) : Observable<GitUserWrapper>
}

