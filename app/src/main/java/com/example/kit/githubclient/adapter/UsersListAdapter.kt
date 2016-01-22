package pl.elpassion.dmalantowicz.rest_client_example.adapter

import com.example.kit.githubclient.dataModels.User


/**
 * Created by dmalantowicz on 15.01.2016.
 */
class UsersListAdapter(val users: List<User> ) : BaseAdapter(){

    init{
        users.forEach {
            adapters.add(UserItemAdapter( it ))
        }
    }
}