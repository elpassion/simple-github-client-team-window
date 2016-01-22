package com.example.kit.githubclient.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kit.githubclient.R
import com.example.kit.githubclient.dataModels.Repository
import pl.elpassion.dmalantowicz.rest_client_example.adapter.ItemAdapter

/**
 * Created by jasiekpor on 22.01.2016.
 */
class RepositoryItemAdapter(private val repository: Repository) : ItemAdapter {


    override val itemViewType = R.id.repository_item_view

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.repository_item_layout, parent, false)
        return RepositoryItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder) {
        val nameRateItemHolder = holder as RepositoryItemHolder
        nameRateItemHolder.name.text = repository.name
    }

    private inner class RepositoryItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.user_text_view) as TextView
    }
}