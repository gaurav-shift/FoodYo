package com.example.foodyo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodyo.presentation.splash.SplashScreen
import androidx.compose.material3.Text

@Composable
fun AppNavigator() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {

        composable<Routes.Splash> {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.Login> {
            Text("Login Screen")
            // LoginScreen()
        }

        composable<Routes.Signup> {
            // SignupScreen()
        }

        composable<Routes.Home> {
            // HomeScreen()
        }
    }
}