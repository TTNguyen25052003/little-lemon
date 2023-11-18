package app.kotlin.littlelemon.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kotlin.littlelemon.R
import app.kotlin.littlelemon.di.LittleLemonRepository
import app.kotlin.littlelemon.ui.screens.FoodItemScreen
import app.kotlin.littlelemon.ui.screens.HomeScreen
import app.kotlin.littlelemon.ui.screens.LoginScreen
import app.kotlin.littlelemon.ui.screens.OnboardingScreen
import app.kotlin.littlelemon.ui.screens.ProfileScreen
import app.kotlin.littlelemon.ui.viewmodels.HomeScreenViewModel
import app.kotlin.littlelemon.ui.viewmodels.HomeScreenViewModelFactory
import app.kotlin.littlelemon.ui.viewmodels.LoginProfileScreenViewModel
import app.kotlin.littlelemon.ui.viewmodels.LoginProfileScreenViewModelFactory

@Composable
fun AppScreen(
    modifier: Modifier,
    context: Context,
) {
    val navController: NavHostController = rememberNavController()
    val listOfLabel: List<String> = listOf(
        stringResource(id = R.string.label_firstname),
        stringResource(id = R.string.label_lastname),
        stringResource(id = R.string.label_email),
        stringResource(id = R.string.label_password)
    )
    val listOfPlaceholder: List<String> = listOf(
        stringResource(id = R.string.placeholder_firstname),
        stringResource(id = R.string.placeholder_lastname),
        stringResource(id = R.string.placeholder_email),
        stringResource(id = R.string.placeholder_password)
    )


    val loginProfileViewModel: LoginProfileScreenViewModel = viewModel(
        factory = LoginProfileScreenViewModelFactory(
            littleLemonRepository = LittleLemonRepository(context = context)
        )
    )

    val homeScreenViewModel: HomeScreenViewModel = viewModel(
        factory = HomeScreenViewModelFactory(
            littleLemonRepository = LittleLemonRepository(context = context)
        )
    )

    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ) {
        composable(route = "LoginScreen") {
            LoginScreen(
                navController = navController,
                modifier = modifier,
                viewModel = loginProfileViewModel
            )
        }

        composable(route = "OnboardingScreen") {
            OnboardingScreen(
                context = context,
                listOfLabel = listOfLabel,
                listOfPlaceholder = listOfPlaceholder,
                navController = navController,
                modifier = modifier,
            )
        }

        composable(route = "HomeScreen") {
            HomeScreen(
                viewModel = homeScreenViewModel,
                navController = navController,
                modifier = modifier
            )
        }

        composable(route = "ProfileScreen") {
            ProfileScreen(
                listOfLabel = listOfLabel.subList(
                    fromIndex = 0,
                    toIndex = 3
                ),
                navController = navController,
                viewModel = loginProfileViewModel,
                modifier = modifier
            )
        }

        composable(route = "FoodItemScreen"){
            FoodItemScreen(
                navController = navController,
                viewModel = homeScreenViewModel,
                modifier = modifier
            )
        }
    }
}