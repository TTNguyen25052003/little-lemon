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
    val listOfLabel:List<String> = listOf(
        stringResource(id = R.string.label_firstname),
        stringResource(id = R.string.label_lastname),
        stringResource(id = R.string.label_email),
        stringResource(id = R.string.label_password)
    )
    val listOfPlaceholder:List<String> = listOf(
        stringResource(id = R.string.placeholder_firstname),
        stringResource(id = R.string.placeholder_lastname),
        stringResource(id = R.string.placeholder_email),
        stringResource(id = R.string.placeholder_password)
    )

    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ) {
        composable(route = "LoginScreen") {
            LoginScreen(navController = navController)
        }

        composable(route = "OnboardingScreen") {
            OnboardingScreen(
                listOfLabel = listOfLabel,
                listOfPlaceholder = listOfPlaceholder,
                navController = navController
            )
        }

        composable(route = "HomeScreen") {
            HomeScreen(navController = navController)
        }

        composable(route = "ProfileScreen") {
            ProfileScreen(
                listOfLabel = listOfLabel.subList(
                    fromIndex = 0,
                    toIndex = 4
                ),
                listOfContent = listOf("Nguyen","Nguyen","Nguyen"),
                navController = navController
            )
        }
    }
}