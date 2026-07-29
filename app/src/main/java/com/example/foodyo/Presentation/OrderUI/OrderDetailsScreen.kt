package com.example.foodyo.Presentation.OrderUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodyo.Presentation.OrderUI.Components.BillDetailsCard
import com.example.foodyo.Presentation.OrderUI.Components.DeliveryAddressCard
import com.example.foodyo.Presentation.OrderUI.Components.OrderHeaderCard
import com.example.foodyo.Presentation.OrderUI.Components.OrderItemsCard
import com.example.foodyo.Presentation.OrderUI.Components.PaymentDetailsCard
import com.example.foodyo.domainLayer.util.Results

@Composable
fun OrderDetailsScreen(
    orderId: String,
    navController: NavController,
    orderViewModel: OrderViewModel = hiltViewModel()
) {
    val orderState by orderViewModel.selectedOrderState.collectAsState()
    LaunchedEffect(Unit) {
        orderViewModel.getOrderById(orderId)
    }
    when (val state = orderState) {
        is Results.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Results.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(state.message)
            }
        }

        is Results.Success -> {
            val order = state.data
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OrderHeaderCard(
                        order = order
                    )
                }
                item {
                    DeliveryAddressCard(
                        address = order.address
                    )
                }
                item {
                    OrderItemsCard(
                        items = order.items
                    )
                }
                item {
                    BillDetailsCard(
                        subtotal = order.subtotal,
                        deliveryFee = order.deliveryFee,
                        tax = order.tax,
                        totalAmount = order.totalAmount
                    )
                }
                item {
                    PaymentDetailsCard(
                        paymentMethod = order.paymentMethod,
                        paymentStatus = order.paymentStatus
                    )
                }
            }
        }
        Results.Idle -> {}
    }
    DisposableEffect(Unit) {
        onDispose {
            orderViewModel.resetSelectedOrderState()
        }
    }
}