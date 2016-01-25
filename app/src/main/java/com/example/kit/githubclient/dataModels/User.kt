package com.example.kit.githubclient.dataModels

import com.google.gson.annotations.SerializedName

/**
 * Created by jasiekpor on 22.01.2016.
 */

class User(@SerializedName("login") override val name: String, val avatar_url: String) : ItemModel
