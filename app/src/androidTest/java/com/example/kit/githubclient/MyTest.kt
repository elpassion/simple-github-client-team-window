package com.example.kit.githubclient

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.test.ActivityInstrumentationTestCase2
import com.example.kit.githubclient.dataModels.Repository
import com.example.kit.githubclient.dataModels.User
import com.example.kit.githubclient.gitService.GitReposService
import com.example.kit.githubclient.gitService.GitUsersService
import com.example.kit.githubclient.gitService.gitReposService
import com.example.kit.githubclient.gitService.gitUserService
import org.junit.Test
import rx.Observable

class MyTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java){

    @Test
    fun testMainListDownload() {
        simpleData()

        activity

        onView(RecyclerViewMatcher(R.id.main_recycler_view)
                .atPositionOnView(1, R.id.user_text_view))
                .check(matches(withText("b")));
    }

    @Test
    fun testUserAvatarOnMainRecyclerView(){
        simpleData()
        activity
        //todo
    }

    private fun simpleData(){
        gitUserService = object:GitUsersService {
            override fun getData(): Observable<List<User>> {
                return Observable.just(listOf<User>(
                        User("a","http://i.imgur.com/Jvh1OQmb.jpg"),
                        User("b","http://i.imgur.com/NUyttbnb.jpg"),
                        User("c","http://i.imgur.com/NUyttbnb.jpg")))
            }
        }

        gitReposService = object:GitReposService {
            override fun getData(): Observable<List<Repository>> {
                return Observable.just(listOf<Repository>(
                        Repository("d"),
                        Repository("e"),
                        Repository("f")
                ))
            }
        }
    }
/*
    private fun click(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
    }

    private fun checkString(id: Int, string: String) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(ViewMatchers.withText(string)))
    }
    */
}
