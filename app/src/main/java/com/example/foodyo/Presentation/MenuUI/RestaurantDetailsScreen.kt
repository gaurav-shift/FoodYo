package com.example.foodyo.Presentation.MenuUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodyo.Presentation.MenuUI.Components.MenuItemCard
import com.example.foodyo.Presentation.MenuUI.Components.RestaurantHeader
import com.example.foodyo.Presentation.MenuUI.Components.RestaurantInfo
import com.example.foodyo.Presentation.MenuUI.Components.RestaurantMetaInfo
import com.example.foodyo.domainLayer.util.Results

@Composable
fun RestaurantDetailsScreen(
    restaurantId: String,
    restaurantViewModel: RestaurantViewModel = hiltViewModel(),
    menuViewModel: MenuViewModel = hiltViewModel(),
    navController: NavController
) {

    val restaurantState by restaurantViewModel.restaurantState.collectAsState()
    val menuState by menuViewModel.menusState.collectAsState()

    LaunchedEffect(restaurantId) {
        restaurantViewModel.getRestaurantById(restaurantId)
        menuViewModel.getMenusByRestaurantId(restaurantId)
    }

    Scaffold { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {

            when (val state = restaurantState) {

                is Results.Success -> {

                    val restaurant = state.data.data ?: return@LazyColumn

                    item {

                        RestaurantHeader(
                            imageUrl = restaurant.image,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onFavouriteClick = {}
                        )

                        RestaurantInfo(
                            modifier = Modifier.padding(16.dp),
                            name = restaurant.name,
                            description = restaurant.description,
                            cuisines = restaurant.cuisine
                        )

                        RestaurantMetaInfo(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            rating = restaurant.rating,
                            deliveryTime = restaurant.deliveryTime,
                            city = restaurant.city,
                            isOpen = restaurant.isOpen
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }

                is Results.Loading -> {

                    item {
                        Text(
                            text = "Loading...",
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }

                is Results.Failure -> {

                    item {
                        Text(
                            text = state.message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }

                else -> {}
            }

            when (val state = menuState) {

                is Results.Success -> {

                    items(state.data.data ?: emptyList()) { menuItem ->

                        MenuItemCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            menuItem = menuItem,
                            quantity = 0,
                            onAddClick = {},
                            onIncrement = {},
                            onDecrement = {}
                        )

                    }

                }

                is Results.Loading -> {

                    item {
                        Text(
                            text = "Loading Menu...",
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }

                is Results.Failure -> {

                    item {
                        Text(
                            text = state.message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }

                else -> {}

            }

        }

    }

}