package app.kotlin.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.ui.screens.HomeScreen
import app.kotlin.littlelemon.ui.screens.LoginScreen
import app.kotlin.littlelemon.ui.screens.OnboardingScreen
import app.kotlin.littlelemon.ui.screens.ProfileScreen

@Composable
fun AppScreen() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ) {
        composable(route = "LoginScreen") {
            LoginScreen(navController = navController)
        }
        composable(route = "OnboardingScreen") {
            OnboardingScreen(
                contentOfButton = "Register",
                navController = navController
            )
        }
        composable(route = "HomeScreen") {
            HomeScreen(navController = navController)
        }
        composable(route = "ProfileScreen") {
            ProfileScreen(
                listOfContent = listOf("Nguyen","Nguyen","Nguyen","Nguyen"),
                navController
            )
        }
    }
}