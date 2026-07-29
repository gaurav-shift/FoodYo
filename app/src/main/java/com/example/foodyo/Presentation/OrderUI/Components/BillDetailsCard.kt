package com.example.foodyo.Presentation.OrderUI.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row

@Composable
fun BillDetailsCard(
    subtotal: Int,
    deliveryFee: Int,
    tax: Int,
    totalAmount: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Bill Details",
                style = MaterialTheme.typography.titleMedium
            )
            BillRow("Subtotal", subtotal)
            BillRow("Delivery Fee", deliveryFee)
            BillRow("Tax", tax)
            HorizontalDivider()
            BillRow(
                title = "Total",
                amount = totalAmount,
                isBold = true
            )
        }
    }
}
@Composable
private fun BillRow(
    title: String,
    amount: Int,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = if (isBold)
                MaterialTheme.typography.titleMedium
            else
                MaterialTheme.typography.bodyMedium

        )
        Text(
            text = "₹$amount",
            style = if (isBold)
                MaterialTheme.typography.titleMedium
            else
                MaterialTheme.typography.bodyMedium
        )
    }
}