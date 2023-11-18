package app.kotlin.littlelemon

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import app.kotlin.littlelemon.ui.AppScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            val modifier: Modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(insets = WindowInsets.statusBars)
                .windowInsetsPadding(insets = WindowInsets.navigationBars)
                .windowInsetsPadding(insets = WindowInsets.ime)
                .background(
                    color = Color.White
                )
            AppScreen(
                modifier = modifier,
                context = applicationContext
            )
        }
    }
}