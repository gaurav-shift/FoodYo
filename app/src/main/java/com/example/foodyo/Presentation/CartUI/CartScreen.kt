package com.example.foodyo.Presentation.CartUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodyo.Presentation.CartUI.Components.CartItemCard
import com.example.foodyo.Presentation.CartUI.Components.CartPriceDetails
import com.example.foodyo.Presentation.CartUI.Components.EmptyCart
import com.example.foodyo.domainLayer.util.Results


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(

    onBackClick: () -> Unit = {},
    onCheckoutClick: () -> Unit = {},

    cartViewModel: CartViewModel = hiltViewModel()

) {

    val cartState by cartViewModel.cartState.collectAsState()

    LaunchedEffect(Unit) {
        cartViewModel.getCart()
    }

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("My Cart")
                }
            )
        }

    ) { paddingValues ->

        when (val state = cartState) {

            Results.Idle -> {}

            Results.Loading -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    CircularProgressIndicator()

                }

            }

            is Results.Failure -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(state.message)

                }

            }

            is Results.Success -> {

                val cart = state.data.data

                if (cart == null || cart.items.isEmpty()) {

                    EmptyCart(
                        onStartShopping = onBackClick
                    )

                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {

                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            item {

                                Text(
                                    text = cart.restaurantName,
                                    style = MaterialTheme.typography.titleLarge
                                )

                            }

                            items(cart.items) { item ->

                                CartItemCard(

                                    item = item,

                                    onIncrement = {

                                        cartViewModel.updateCartItemQuantity(
                                            menuId = item.menuId,
                                            quantity = item.quantity + 1
                                        )

                                    },

                                    onDecrement = {

                                        if (item.quantity == 1) {

                                            cartViewModel.removeCartItem(
                                                item.menuId
                                            )

                                        } else {

                                            cartViewModel.updateCartItemQuantity(
                                                menuId = item.menuId,
                                                quantity = item.quantity - 1
                                            )

                                        }

                                    }

                                )

                            }

                            item {

                                CartPriceDetails(cart)

                            }

                        }

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = onCheckoutClick
                        ) {

                            Text("Proceed to Checkout")

                        }

                    }

                }

            }

        }

    }

}