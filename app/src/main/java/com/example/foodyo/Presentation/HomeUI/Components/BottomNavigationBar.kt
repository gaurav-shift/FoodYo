package com.example.foodyo.Presentation.HomeUI.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.foodyo.ui.theme.OrangeStart

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {

    val items = listOf(
        "Home",
        "Orders",
        "Cart",
        "Profile"
    )

    val icons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.ReceiptLong,
        Icons.Outlined.ShoppingCart,
        Icons.Outlined.Person
    )

    NavigationBar(
        containerColor = Color.Black
    ) {

        items.forEachIndexed { index, title ->

            NavigationBarItem(

                selected = selectedIndex == index,

                onClick = {
                    onItemClick(index)
                },

                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = title
                    )
                },

                label = {
                    Text(title)
                },

                alwaysShowLabel = true

            )

        }

    }

}