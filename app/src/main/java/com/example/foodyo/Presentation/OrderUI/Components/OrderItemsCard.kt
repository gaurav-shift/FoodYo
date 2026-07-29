package com.example.foodyo.Presentation.OrderUI.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.foodyo.dataLayer.remote.dto.order.OrderItemDto
import kotlin.collections.forEachIndexed
import kotlin.collections.lastIndex
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun OrderItemsCard(
    items: List<OrderItemDto>,
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Order Items",
                style = MaterialTheme.typography.titleMedium
            )
            items.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodyLarge

                        )
                        Text(
                            text = "Qty : ${item.quantity}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Text(
                        text = "₹${item.price * item.quantity}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (index != items.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}