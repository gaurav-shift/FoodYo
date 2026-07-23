package com.example.foodyo.Presentation.MenuUI.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RestaurantMetaInfo(
    rating: Double,
    deliveryTime: Int,
    city: String,
    isOpen: Boolean,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        MetaChip(
            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107)
                )
            },
            text = rating.toString()
        )

        MetaChip(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            text = "$deliveryTime mins"
        )

        MetaChip(
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            text = city
        )

        MetaChip(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Circle,
                    contentDescription = null,
                    tint = if (isOpen)
                        Color(0xFF4CAF50)
                    else
                        Color.Red
                )
            },
            text = if (isOpen) "Open" else "Closed"
        )

    }

}

@Composable
private fun MetaChip(
    icon: @Composable () -> Unit,
    text: String
) {

    Surface(
        color = Color(0xFF1E1E1E),
        shape = CircleShape
    ) {

        Row(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            icon()

            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )

        }

    }

}