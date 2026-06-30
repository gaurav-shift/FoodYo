package com.example.foodyo.Presentation.AddressUI.Component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodyo.ui.theme.OrangeStart

@Composable
fun AddressLabelSelector(
    selectedLabel: String,
    onLabelSelected: (String) -> Unit
) {

    val labels = listOf("Home", "Work", "Other")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Row(
           // modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            labels.forEach { label ->

                FilterChip(
                    selected = selectedLabel == label,
                    onClick = {
                        onLabelSelected(label)
                    },
                    label = {
                        Text(label)
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = OrangeStart,
                        selectedLabelColor = Color.White
                    )
                )

            }
        }
    }
}