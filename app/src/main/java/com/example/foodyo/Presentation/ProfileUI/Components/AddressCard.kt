package com.example.foodyo.Presentation.ProfileUI.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodyo.dataLayer.remote.dto.address.AddressDto
import com.example.foodyo.ui.theme.OrangeStart

@Composable
fun AddressCard(
    address: AddressDto,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onMakeDefault: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null,
                        tint = OrangeStart
                    )

                    Text(
                        text = address.label,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                if (address.isDefault) {

                    Text(
                        text = "Default",
                        color = OrangeStart,
                        style = MaterialTheme.typography.labelMedium
                    )

                }

            }

            Text(
                text = address.receiverName,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = address.phone,
                color = Color.LightGray
            )

            Text(
                text = address.addressLine1,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )

            if (address.addressLine2.isNotBlank()) {

                Text(
                    text = address.addressLine2,
                    color = Color.White
                )

            }

            Text(
                text = "${address.city}, ${address.state} - ${address.pincode}",
                color = Color.LightGray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {

                    TextButton(
                        onClick = onEdit
                    ) {
                        Text("Edit")
                    }

                    TextButton(
                        onClick = onDelete
                    ) {
                        Text("Delete")
                    }

                }

                if (!address.isDefault) {

                    TextButton(
                        onClick = onMakeDefault
                    ) {
                        Text("Make Default")
                    }

                }

            }

        }

    }

}