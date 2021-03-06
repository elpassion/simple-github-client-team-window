package com.example.kit.githubclient

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.widget.CheckBox
import android.widget.EditText
import com.crashlytics.android.Crashlytics
import com.example.kit.githubclient.adapter.ItemListAdapter
import com.example.kit.githubclient.dataModels.ItemModel
import com.example.kit.githubclient.dataModels.User
import com.example.kit.githubclient.gitService.GitHubServiceManager
import de.greenrobot.event.EventBus
import io.fabric.sdk.android.Fabric
import java.util.*

class MainActivity : AppCompatActivity() {
    val mainRecyclerView by lazy { findViewById(R.id.main_recycler_view) as RecyclerView }
    val editText by lazy { findViewById(R.id.edit_text) as EditText }
    val activity by lazy { this }
    val onlyUsersCheckBox by lazy { findViewById(R.id.users_only_check_box) as CheckBox }
    var usersAndReposList: List<ItemModel> = listOfNotNull()
    val onlyUsersList: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics());
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)

        editText.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //todo pobranie danych o 1 userze
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //throw UnsupportedOperationException()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //throw UnsupportedOperationException()
            }
        })

        onlyUsersCheckBox.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                loadRecyclerView(onlyUsersList)
            } else {
                loadRecyclerView(usersAndReposList)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GitHubServiceManager(this).getMainList()
    }

    public fun loadUsersAndReposList(itemList: List<ItemModel>) {
        usersAndReposList = itemList
        for (item in usersAndReposList)
            if (item is User)
                onlyUsersList.add(item)
        loadRecyclerView(usersAndReposList)
    }

    private fun loadRecyclerView(itemList: List<ItemModel>) {
        mainRecyclerView.layoutManager = LinearLayoutManager(mainRecyclerView.context)
        mainRecyclerView.adapter = ItemListAdapter(itemList)
    }

    public fun printMessege(messege: String) {
        Snackbar.make(mainRecyclerView, messege, Snackbar.LENGTH_LONG).show()
    }

    public fun onEvent(message: MessageEvent) {
        UserDetailsActivity.start(this, message.user)
    }
}

