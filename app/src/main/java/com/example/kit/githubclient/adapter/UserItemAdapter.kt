package com.example.kit.githubclient.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kit.githubclient.MessageEvent
import com.example.kit.githubclient.R
import com.example.kit.githubclient.dataModels.User
import de.greenrobot.event.EventBus

/**
 * Created by dmalantowicz on 15.01.2016.
 */
class UserItemAdapter(private val user: User) : ItemAdapter {


    override val itemViewType = R.id.user_item_view

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.user_item_layout, parent, false)
        view.setOnClickListener{
            EventBus.getDefault().post(MessageEvent(user))
        }
        return UserItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder) {
        val nameRateItemHolder = holder as UserItemHolder
        Glide.with(nameRateItemHolder.icon.context)
                .load(user.avatar_url)
                .into(nameRateItemHolder.icon)
        nameRateItemHolder.login.text = user.name
    }

    private inner class UserItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val login = itemView.findViewById(R.id.user_text_view) as TextView
        val icon = itemView.findViewById(R.id.user_image_view) as ImageView
    }
}