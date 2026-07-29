package com.example.foodyo.Presentation.OrderUI.Components

import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun PaymentDetailsCard(
    paymentMethod: String,
    paymentStatus: String,
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
                text = "Payment",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Method : $paymentMethod",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Status : $paymentStatus",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}