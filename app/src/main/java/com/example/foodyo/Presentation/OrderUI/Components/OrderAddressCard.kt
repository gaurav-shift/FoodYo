package com.example.foodyo.Presentation.OrderUI.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodyo.dataLayer.remote.dto.address.AddressDto
import com.example.foodyo.ui.theme.OrangeStart

@Composable
fun OrderAddressCard(
    address: AddressDto,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) OrangeStart else Color.DarkGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null,
                    tint = OrangeStart
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = address.label,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                RadioButton(
                    selected = isSelected,
                    onClick = onClick
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = address.receiverName,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = address.phone,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = address.addressLine1,
                color = Color.White
            )

            if (address.addressLine2.isNotBlank()) {
                Text(
                    text = address.addressLine2,
                    color = Color.White
                )
            }

            Text(
                text = "${address.city}, ${address.state} - ${address.pincode}",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyMedium
            )

        }

    }

}