package com.example.foodyo.dataLayer.remote.dto.menu

import kotlinx.serialization.Serializable

@Serializable
data class MenuDto(

    val id: String,

    val restaurantId: String,

    val name: String,

    val description: String,

    val image: String,

    val category: String,

    val price: Int,

    val isVeg: Boolean,

    val isAvailable: Boolean,

    val preparationTime: Int

)