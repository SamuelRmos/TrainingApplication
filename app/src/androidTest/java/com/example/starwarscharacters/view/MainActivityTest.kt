package com.example.starwarscharacters.view

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.starwarscharacters.R
import com.example.starwarscharacters.base.BaseUITest
import com.example.starwarscharacters.di.generateTestAppComponent
import com.example.starwarscharacters.helpers.recyclerItemAtPosition
import com.example.starwarscharacters.repository.MainRepository
import com.example.starwarscharacters.view.activity.MainActivity
import com.example.starwarscharacters.view.adapter.MainRecyclerViewAdapter
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseUITest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(
        MainActivity::class.java,
        true,
        false
    )

    private val mNameTestOne = "Luke Skywalker"
    private val mMassTestOne = "77"
    private val mNameTestTwo = "Obi-Wan Kenobi"
    private val mMassTestTwo = "77"

    @Before
    fun start() {
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()))
    }

    @Test
    fun test_recyclerview_elements_for_expected_response() {

        mActivityTestRule.launchActivity(null)

        mockNetworkResponseWithFileContent("success_resp_list", HttpURLConnection.HTTP_OK)

        SystemClock.sleep(1000)

        onView(withId(R.id.recycler_view))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mNameTestOne))
                    )
                )
            )

        onView(withId(R.id.recycler_view))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mMassTestOne))
                    )
                )
            )

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.scrollToPosition<MainRecyclerViewAdapter.MainViewHolder>(9)
        )

        onView(withId(R.id.recycler_view))
            .check(
                matches(
                    recyclerItemAtPosition(
                        9,
                        ViewMatchers.hasDescendant(withText(mNameTestTwo))
                    )
                )
            )

        onView(withId(R.id.recycler_view))
            .check(
                matches(
                    recyclerItemAtPosition(
                        9,
                        ViewMatchers.hasDescendant(withText(mMassTestTwo))
                    )
                )
            )
    }
}