package com.example.kit.githubclient.gitService

import com.example.kit.githubclient.dataModels.User
import retrofit2.http.GET
import rx.Observable


interface GitUsersService {
    @GET("users")
    fun getData () : Observable<List<User>>
}

