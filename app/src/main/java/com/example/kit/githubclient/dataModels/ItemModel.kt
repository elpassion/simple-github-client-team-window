package com.example.kit.githubclient.dataModels

/**
 * Created by jasiekpor on 25.01.2016.
 */
interface ItemModel : Comparable<ItemModel>{
    fun getItemName():String
    override fun compareTo(other: ItemModel): Int {
        return this.getItemName().toLowerCase().compareTo(other.getItemName().toLowerCase())
    }
}