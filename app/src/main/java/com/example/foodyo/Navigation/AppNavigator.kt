package com.example.foodyo.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodyo.Presentation.AuthUI.LoginScreen
import com.example.foodyo.Presentation.AuthUI.SignUpScreen
import com.example.foodyo.Presentation.Splash.SplashScreen

@Composable
fun AppNavigator() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = routes.Splash
    ) {

        composable<routes.Splash> {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(routes.Login) {
                        popUpTo(routes.Splash) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(routes.Home) {
                        popUpTo(routes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<routes.Login> {

             LoginScreen(
                navController = navController
             )
        }

        composable<routes.Signup> {
            SignUpScreen(navController)
        }

        composable<routes.Home> {
            // HomeScreen()
        }
    }
}