package com.example.foodyo.Presentation.OrderUI.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import java.text.SimpleDateFormat
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodyo.dataLayer.remote.dto.order.OrderDto
import java.util.Locale

@Composable
fun OrderCard(
    order: OrderDto,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = order.restaurantName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "${order.items.sumOf { it.quantity }} Items"
            )
            Text(
                text = "₹${order.totalAmount}"
            )
            Text(
                text = order.orderStatus
            )
            Text(
                text = formatDate(order.createdAt)
            )
        }
    }
}

fun formatDate(date: String): String {

    return try {

        val input =
            SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            )

        val output =
            SimpleDateFormat(
                "dd MMM yyyy",
                Locale.getDefault()
            )

        output.format(input.parse(date)!!)

    } catch (e: Exception) {

        date

    }

}