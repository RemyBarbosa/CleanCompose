package com.cleancompose.weather.ui.list

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cleancompose.application.TestMainActivity
import com.cleancompose.weather.WeatherListScreen
import junit.framework.TestCase.fail
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherListScreenTest {

    private val server = MockWebServer()

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestMainActivity>()

    @Before
    fun setup() {
        server.start()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun testWeatherListScreen() {
        val response = readJsonResponse("response.json")
        server.enqueue(MockResponse().setBody(response))

        composeTestRule.setContent {
            WeatherListScreen(navController = rememberNavController())
        }

        val maxAttempts = 10
        var attempt = 0
        var isDataLoaded = false

        while (attempt < maxAttempts && !isDataLoaded) {
            try {
                composeTestRule.onAllNodesWithText("sky is clear").assertCountEquals(1)
                isDataLoaded = true
            } catch (e: AssertionError) {
                attempt++
                Thread.sleep(500)
            }
        }

        if (!isDataLoaded) {
            fail("The data was not loaded within the expected time.")
        }
    }
}

fun readJsonResponse(fileName: String): String {
    val inputStream = InstrumentationRegistry.getInstrumentation().context.assets.open(fileName)
    return inputStream.bufferedReader().use { it.readText() }
}
