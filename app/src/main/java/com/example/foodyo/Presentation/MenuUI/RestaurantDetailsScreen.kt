package com.example.foodyo.Presentation.MenuUI
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodyo.Presentation.CartUI.CartViewModel
import com.example.foodyo.Presentation.MenuUI.Components.MenuItemCard
import com.example.foodyo.Presentation.MenuUI.Components.RestaurantHeader
import com.example.foodyo.Presentation.MenuUI.Components.RestaurantInfo
import com.example.foodyo.Presentation.MenuUI.Components.RestaurantMetaInfo
import com.example.foodyo.domainLayer.util.Results
import androidx.compose.runtime.setValue

@Composable
fun RestaurantDetailsScreen(
    restaurantId: String,
    restaurantViewModel: RestaurantViewModel = hiltViewModel(),
    menuViewModel: MenuViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    navController: NavController
) {

    val restaurantState by restaurantViewModel.restaurantState.collectAsState()
    val menuState by menuViewModel.menusState.collectAsState()
    val cart by cartViewModel.cart.collectAsState()
    val cartItemsMap = remember(cart) {
        cart?.items?.associateBy { it.menuId } ?: emptyMap()
    }
    var showReplaceCartDialog by remember {
        mutableStateOf(false)
    }

    var pendingMenuId by remember {
        mutableStateOf<String?>(null)
    }
    val cartState by cartViewModel.cartState.collectAsState()

    LaunchedEffect(cartState) {

        if (cartState is Results.Failure) {
            val message = (cartState as Results.Failure).message
            if (message.contains("another restaurant", ignoreCase = true)) {
                showReplaceCartDialog = true
            }
        }
    }

    LaunchedEffect(restaurantId) {
        restaurantViewModel.getRestaurantById(restaurantId)
        menuViewModel.getMenusByRestaurantId(restaurantId)
        cartViewModel.getCart()
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

                        val quantity =
                            cartItemsMap[menuItem.id]?.quantity ?: 0
                        MenuItemCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            menuItem = menuItem,
                            quantity = quantity,
                            onAddClick = {
                                pendingMenuId = menuItem.id
                                cartViewModel.addToCart(
                                    menuItem.id
                                )
                            },
                            onIncrement = {
                                cartViewModel.updateCartItemQuantity(
                                    menuId = menuItem.id,
                                    quantity = quantity + 1
                                )
                            },

                            onDecrement = {
                                if (quantity == 1) {
                                    cartViewModel.removeCartItem(
                                        menuItem.id
                                    )
                                } else {
                                    cartViewModel.updateCartItemQuantity(
                                        menuId = menuItem.id,
                                        quantity = quantity - 1
                                    )
                                }
                            }
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
    if (showReplaceCartDialog) {
        AlertDialog(
            onDismissRequest = {
                showReplaceCartDialog = false
            },
            title = {
                Text("Replace cart?")
            },
            text = {
                Text(
                    "Your cart contains items from another restaurant.\n\nDo you want to clear your cart and add this item?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showReplaceCartDialog = false
                        cartViewModel.clearCart {
                            pendingMenuId?.let {
                                cartViewModel.addToCart(it)
                            }
                        }
                    }
                ) {
                    Text("Replace")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showReplaceCartDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )

    }
}