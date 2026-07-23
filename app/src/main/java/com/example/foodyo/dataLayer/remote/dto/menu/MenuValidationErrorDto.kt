package com.example.foodyo.dataLayer.remote.dto.menu

import kotlinx.serialization.Serializable

@Serializable
data class MenuValidationErrorDto(

    val type: String,

    val msg: String,

    val path: String,

    val location: String

)