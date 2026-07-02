package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentUserResponseDto(
    val success: Boolean,
    val message: String,
    val data: UserProfileDto?,
    val error: ErrorDto?
)