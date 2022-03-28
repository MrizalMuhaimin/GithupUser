package com.example.githupuser

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.githupuser.intent.ListUserActivity
import com.example.githupuser.intent.SettingActivity
import kotlinx.coroutines.delay
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @Before
    fun setup(){
        ActivityScenario.launch(ListUserActivity::class.java)
    }
    @Test
    fun cliikButton(){
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.iv_setting)).perform(click())

    }




}