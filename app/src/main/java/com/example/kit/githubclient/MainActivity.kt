package com.example.kit.githubclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.example.kit.githubclient.gitService.GitHubServiceManager
import io.fabric.sdk.android.Fabric

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics());
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        GitHubServiceManager().getMainList()
    }
}

