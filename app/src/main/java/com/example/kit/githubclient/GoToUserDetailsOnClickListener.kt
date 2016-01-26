package com.example.kit.githubclient

import android.view.View
import com.example.kit.githubclient.dataModels.User

/**
 * Created by jasiekpor on 25.01.2016.
 */
class GoToUserDetailsOnClickListener(val user: User) : View.OnClickListener {
    override fun onClick(v: View) {
        UserDetailsActivity.start(v.context, user)
    }
}