package com.example.foodyo.Presentation.HomeUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodyo.Navigation.routes
import com.example.foodyo.Presentation.HomeUI.Components.BottomNavigationBar
import com.example.foodyo.Presentation.HomeUI.Components.HomeTopBar
import com.example.foodyo.Presentation.HomeUI.Components.RestaurantCard
import com.example.foodyo.Presentation.HomeUI.Components.SearchBar
import com.example.foodyo.domainLayer.util.Results

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val restaurantState by viewModel.restaurantState.collectAsState()
    val defaultAddress by viewModel.defaultAddress.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHome()
    }

    Scaffold(
        containerColor = Color.Black,

        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedIndex,
                onItemClick = { index->
                    selectedIndex = index

                    when(index){

                        0 -> navController.navigate(routes.Home)

                     //   1 -> navController.navigate(routes.Orders)

                     //   2 -> navController.navigate(routes.Cart)

                        3 -> navController.navigate(routes.Profile)

                    }
                }
            )
        }

    ) { paddingValues ->

        when (val state = restaurantState) {

            is Results.Loading -> {

                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }

            else -> {

                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(Color.Black)
                ) {

                    item {

                        HomeTopBar(
                            addressLabel = defaultAddress?.label ?: "",
                            city = defaultAddress?.city ?: "",
                            onProfileClick = {
                                // TODO
                            }
                        )

                    }

                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    item {

                        SearchBar(
                            onClick = {
                                // TODO
                            }
                        )

                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    item {

                        Text(
                            text = "Popular Restaurants",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                    }

                    when (state) {

                        is Results.Success -> {

                            items(state.data.data ?: emptyList()) { restaurant ->

                                RestaurantCard(
                                    restaurant = restaurant,
                                    onClick = {
                                        // TODO Restaurant Details
                                    }
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

                        is Results.Idle -> {}

                        else -> {}

                    }

                }

            }

        }

    }

}