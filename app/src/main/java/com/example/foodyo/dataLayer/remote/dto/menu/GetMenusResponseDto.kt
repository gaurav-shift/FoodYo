package com.example.foodyo.dataLayer.remote.dto.menu

import kotlinx.serialization.Serializable

@Serializable
data class GetMenusResponseDto(

    val success: Boolean,

    val message: String,

    val data: List<MenuDto>? = null,

    val error: MenuErrorDto? = null

)