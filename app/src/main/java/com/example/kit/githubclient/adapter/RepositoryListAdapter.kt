package com.example.kit.githubclient.adapter

import com.example.kit.githubclient.dataModels.Repository
import pl.elpassion.dmalantowicz.rest_client_example.adapter.BaseAdapter

/**
 * Created by jasiekpor on 22.01.2016.
 */
class RepositoryListAdapter (val repos: List<Repository> ) : BaseAdapter(){

    init{
        repos.forEach {
            adapters.add(RepositoryItemAdapter(it))
        }
    }
}