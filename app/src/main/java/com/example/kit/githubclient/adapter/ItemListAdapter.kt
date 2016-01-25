package com.example.kit.githubclient.adapter

import com.example.kit.githubclient.dataModels.ItemModel
import com.example.kit.githubclient.dataModels.Repository
import com.example.kit.githubclient.dataModels.User


/**
 * Created by jasiekpor on 25.01.2016.
 */
    class ItemListAdapter(val items:List<ItemModel>): BaseAdapter() {
    init{
        items.forEach {
            if(it is User)
            adapters.add(UserItemAdapter(it))
            else if(it is Repository)
                adapters.add(RepositoryItemAdapter(it))
        }
    }
}

