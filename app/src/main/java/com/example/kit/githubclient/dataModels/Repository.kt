package com.example.kit.githubclient.dataModels

/**
 * Created by jasiekpor on 22.01.2016.
 */

class Repository(val name: String) : ItemModel{
    override fun getItemName(): String {
        return name
    }
}


