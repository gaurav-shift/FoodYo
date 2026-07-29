package com.example.foodyo.Presentation.OrderUI.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodyo.dataLayer.remote.dto.order.OrderDto

@Composable
fun OrderHeaderCard(
    order: OrderDto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = order.restaurantName,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Order ID : ${order.id}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Ordered On : ${order.createdAt}",
                style = MaterialTheme.typography.bodySmall
            )
            // Backend status properly aaye tab enable karenge
            Text(
                text = order.orderStatus,
                style = MaterialTheme.typography.labelLarge
            )

        }

    }

}