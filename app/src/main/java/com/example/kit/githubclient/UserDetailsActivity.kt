package com.example.kit.githubclient

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kit.githubclient.adapter.ItemListAdapter
import com.example.kit.githubclient.dataModels.Repository
import com.example.kit.githubclient.dataModels.User
import com.example.kit.githubclient.gitService.GitHubServiceManager

/**
 * Created by jasiekpor on 25.01.2016.
 */
class UserDetailsActivity : Activity() {
    companion object{
        val userKey = "user"
        public fun start(context: Context, user: User){
            val intent = Intent(context,UserDetailsActivity::class.java)
            intent.putExtra(userKey,user)
            context.startActivity(intent)
        }
    }
    val detailsRecyclerView by lazy { findViewById(R.id.user_details_recycler_view) as RecyclerView }
    val userAvatar by lazy { findViewById(R.id.user_details_image_view) as ImageView}
    val activity by lazy {this}
    override fun onCreate(savedInstanceState: Bundle, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.user_detail_view_layout)
        val user = savedInstanceState.getParcelable<User>(userKey)
        Glide.with(userAvatar.context)
                .load(user.avatar_url)
                .into(userAvatar)
        GitHubServiceManager(activity).getReposForUser(user.name)
    }
    public fun loadRecyclerView(itemList : List<Repository>){
        detailsRecyclerView.layoutManager = LinearLayoutManager(detailsRecyclerView.context)
        detailsRecyclerView.adapter = ItemListAdapter(itemList)
    }
}