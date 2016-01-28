package com.example.kit.githubclient

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.test.ActivityInstrumentationTestCase2
import com.example.kit.githubclient.dataModels.Repository
import com.example.kit.githubclient.dataModels.User
import com.example.kit.githubclient.gitService.*
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import rx.Observable

class MyTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {

    @Test
    fun testMainListDownload() {
        sampleData()

        activity

        onView(RecyclerViewMatcher(R.id.main_recycler_view)
                .atPositionOnView(1, R.id.user_text_view))
                .check(matches(withText("b")));
    }

    @Test
    fun testUserAvatarOnMainRecyclerView() {
        sampleData()
        activity
        onView(allOf(
                isDescendantOfA(allOf(
                        withParent(withId(R.id.main_recycler_view)),
                        hasDescendant(allOf(
                                withId(R.id.user_text_view),
                                withText("b"))
                        )
                )),
                withId(R.id.user_image_view))
        ).check(matches(isDisplayed()))
    }

    @Test
    fun testUserAvatarOnUserDetailScreen(){
        sampleData()
        activity
        onView(withId(R.id.main_recycler_view))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.user_details_image_view))
        .check(matches(isDisplayed()))
    }
    @Test
    fun testOnlyUsersInRecyclerView(){
        sampleData()
        activity
        onView(withId(R.id.users_only_check_box))
        .perform(click())
        onView(withId(R.id.repository_item_view))
                .check(doesNotExist())
    }

    private fun sampleData() {
        gitUserService = object : GitUsersService {
            override fun getData(): Observable<List<User>> {
                return Observable.just(listOf<User>(
                        User("a", "http://i.imgur.com/Jvh1OQmb.jpg"),
                        User("b", "http://i.imgur.com/NUyttbnb.jpg"),
                        User("c", "http://i.imgur.com/NUyttbnb.jpg")))
            }
        }

        gitReposService = object : GitReposService {
            override fun getData(): Observable<List<Repository>> {
                return Observable.just(listOf<Repository>(
                        Repository("d"),
                        Repository("e"),
                        Repository("f")
                ))
            }
        }

        gitUserDataService = object :GitUserDataService{
            override fun getData(name: String): Observable<List<Repository>> {
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
