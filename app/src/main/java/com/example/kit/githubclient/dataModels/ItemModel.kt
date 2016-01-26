package com.example.kit.githubclient.dataModels

/**
 * Created by jasiekpor on 25.01.2016.
 */
interface ItemModel : Comparable<ItemModel> {
    val name: String
    override fun compareTo(other: ItemModel): Int {
        return this.name.toLowerCase().compareTo(other.name.toLowerCase())
    }
}