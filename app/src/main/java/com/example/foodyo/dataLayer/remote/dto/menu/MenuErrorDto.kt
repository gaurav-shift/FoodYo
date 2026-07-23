package com.example.foodyo.dataLayer.remote.dto.menu

import kotlinx.serialization.Serializable

@Serializable
data class MenuErrorDto(

    val statusCode: Int? = null,

    val code: Int? = null,

    val details: List<MenuValidationErrorDto>? = null

)