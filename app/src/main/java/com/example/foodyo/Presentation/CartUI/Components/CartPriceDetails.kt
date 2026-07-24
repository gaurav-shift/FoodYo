package com.example.foodyo.Presentation.CartUI.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodyo.dataLayer.remote.dto.cart.CartDto

@Composable
fun CartPriceDetails(
    cart: CartDto
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        PriceRow(
            title = "Subtotal",
            value = "₹${cart.subtotal}"
        )

        PriceRow(
            title = "Delivery Fee",
            value = "₹${cart.deliveryFee}"
        )

        PriceRow(
            title = "Tax",
            value = "₹${cart.tax}"
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp)
        )

        PriceRow(
            title = "Total",
            value = "₹${cart.totalAmount}",
            isBold = true
        )

    }

}

@Composable
private fun PriceRow(
    title: String,
    value: String,
    isBold: Boolean = false
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            style = if (isBold)
                MaterialTheme.typography.titleMedium
            else
                MaterialTheme.typography.bodyLarge,
            fontWeight = if (isBold)
                FontWeight.Bold
            else
                FontWeight.Normal
        )

        Text(
            text = value,
            style = if (isBold)
                MaterialTheme.typography.titleMedium
            else
                MaterialTheme.typography.bodyLarge,
            fontWeight = if (isBold)
                FontWeight.Bold
            else
                FontWeight.Normal
        )

    }

}