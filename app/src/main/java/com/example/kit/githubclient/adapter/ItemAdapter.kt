package pl.elpassion.dmalantowicz.rest_client_example.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface ItemAdapter {
    val itemViewType: Int
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder)
}
