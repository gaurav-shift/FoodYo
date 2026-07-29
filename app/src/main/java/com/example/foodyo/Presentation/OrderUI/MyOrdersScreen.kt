package com.example.foodyo.Presentation.OrderUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.example.foodyo.Navigation.routes
import com.example.foodyo.Presentation.OrderUI.Components.OrderCard
import com.example.foodyo.domainLayer.util.Results


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOrdersScreen(
    navController: NavController,
    orderViewModel: OrderViewModel = hiltViewModel()
) {

    val ordersState by
    orderViewModel.ordersState.collectAsState()
    LaunchedEffect(Unit) {
        orderViewModel.getOrders()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("My Orders")
                }
            )
        }
    ) { padding ->

        when (val state = ordersState) {

            Results.Idle -> {}
            Results.Loading -> {

                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Results.Failure -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(state.message)
                }
            }
            is Results.Success -> {
                if (state.data.isEmpty()) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No Orders Yet")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(padding),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        items(state.data) { order ->

                            OrderCard(

                                order = order,

                                onClick = {

                                    navController.navigate(

                                        routes.OrderDetails(
                                            order.id
                                        )

                                    )

                                }

                            )

                        }

                    }

                }

            }

        }

    }

}