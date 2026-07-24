package com.example.foodyo.Navigation

import ProfileScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.foodyo.Presentation.AddressUI.CreateAddressScreen
import com.example.foodyo.Presentation.AddressUI.UpdateAddressScreen
import com.example.foodyo.Presentation.AuthUI.LoginScreen
import com.example.foodyo.Presentation.AuthUI.SignUpScreen
import com.example.foodyo.Presentation.CartUI.CartScreen
import com.example.foodyo.Presentation.HomeUI.HomeScreen
import com.example.foodyo.Presentation.MenuUI.RestaurantDetailsScreen
import com.example.foodyo.Presentation.Splash.SplashScreen
import com.example.foodyo.dataLayer.remote.dto.address.AddressDto

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
            HomeScreen(
                navController = navController
            )
        }
        composable<routes.CreateAddress> {
            CreateAddressScreen(
                navController = navController
            )
        }

        composable<routes.Profile> {
            ProfileScreen(
                navController = navController
            )
        }

        composable<routes.UpdateAddress> {

            val address =
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<AddressDto>("address")

            if (address != null) {
                UpdateAddressScreen(
                    address = address,
                    navController = navController
                )
            }

        }

        composable<routes.RestaurantDetails> { backStackEntry ->

            val route = backStackEntry.toRoute<routes.RestaurantDetails>()

            RestaurantDetailsScreen(
                restaurantId = route.restaurantId,
                navController = navController
            )
        }
        composable< routes.Cart> {
            CartScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onCheckoutClick = {
                    // TODO
                }
            )
        }

    }
}