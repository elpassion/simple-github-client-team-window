package com.example.kit.githubclient

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.RecyclerView
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

/**
 * Created by jasiekpor on 25.01.2016.
 */
class UserDetailsActivity : Activity() {
    val detailsRecyclerView by lazy { findViewById(R.id.user_details_recycler_view) as RecyclerView }
    val userAvatar by lazy { findViewById(R.id.user_details_image_view) }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Fabric.with(this, Crashlytics());
        setContentView(R.layout.user_detail_view_layout)
    }
}