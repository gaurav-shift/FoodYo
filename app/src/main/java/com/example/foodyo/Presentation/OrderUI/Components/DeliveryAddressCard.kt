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
import com.example.foodyo.dataLayer.remote.dto.order.AddressDto

@Composable
fun DeliveryAddressCard(
    address: AddressDto,
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
                text = "Delivery Address",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Phone : ${address.phone}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${address.city}, ${address.state}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Pincode : ${address.pincode}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}