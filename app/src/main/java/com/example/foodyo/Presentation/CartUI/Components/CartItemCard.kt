package com.example.foodyo.Presentation.CartUI.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodyo.dataLayer.remote.dto.cart.CartItemDto

@Composable
fun CartItemCard(
    item: CartItemDto,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            Color.LightGray
        ),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = if (item.isVeg) "🟢 Veg" else "🔴 Non Veg",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Text(
                        text = "₹${item.price}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Card(
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.primary
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            onClick = onDecrement
                        ) {

                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Decrease"
                            )

                        }

                        Text(
                            text = item.quantity.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        IconButton(
                            onClick = onIncrement
                        ) {

                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase"
                            )

                        }

                    }

                }

            }

        }

    }

}