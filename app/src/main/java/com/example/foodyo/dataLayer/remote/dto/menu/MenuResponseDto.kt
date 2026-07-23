package com.example.foodyo.dataLayer.remote.dto.menu

import kotlinx.serialization.Serializable

@Serializable
data class MenuResponseDto(

    val success: Boolean,

    val message: String,

    val data: MenuDto? = null,

    val error: MenuErrorDto? = null

)