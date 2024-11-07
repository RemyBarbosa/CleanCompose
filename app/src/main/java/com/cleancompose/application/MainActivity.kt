package com.cleancompose.application

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.cleancompose.ui.theme.CleanComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepNativeSplashScreenVisible by mutableStateOf(true)
    private var keepSplashScreenVisible by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { keepNativeSplashScreenVisible }

        setContent {

            CleanComposeTestTheme {
                NavigationGraph()
            }
            AnimatedVisibility(
                visible = keepSplashScreenVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CustomSplashScreen {
                    keepNativeSplashScreenVisible = false
                    lifecycleScope.launch {
                        delay(3000)
                        keepSplashScreenVisible = false
                    }
                }
            }

        }

    }
}



