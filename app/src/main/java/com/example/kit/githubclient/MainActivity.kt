package com.example.kit.githubclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.crashlytics.android.Crashlytics
import com.example.kit.githubclient.adapter.ItemListAdapter
import com.example.kit.githubclient.dataModels.ItemModel
import com.example.kit.githubclient.gitService.GitHubServiceManager
import io.fabric.sdk.android.Fabric



class MainActivity : AppCompatActivity() {
    val mainRecyclerView by lazy { findViewById(R.id.main_recycler_view) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics());
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        GitHubServiceManager(this).getMainList()
    }

    public fun loadRecyclerView(itemList : List<ItemModel>){
        mainRecyclerView.layoutManager = LinearLayoutManager(mainRecyclerView.context)
        mainRecyclerView.adapter = ItemListAdapter(itemList)
    }
}

