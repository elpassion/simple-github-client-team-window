package com.example.kit.githubclient.dataModels

/**
 * Created by jasiekpor on 22.01.2016.
 */

class User(val login: String, val avatar_url: String) : ItemModel{
    override fun getItemName(): String {
        return login
    }
}
