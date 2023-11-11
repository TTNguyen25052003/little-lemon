package app.kotlin.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.kotlin.littlelemon.ui.AppScreen
import app.kotlin.littlelemon.ui.screens.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}