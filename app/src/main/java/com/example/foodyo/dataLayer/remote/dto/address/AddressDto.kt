package com.example.foodyo.dataLayer.remote.dto.address

import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import android.os.Parcelable

@Parcelize
@Serializable
data class AddressDto(
    val id: String,
    val label: String,
    val receiverName: String,
    val phone: String,
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val state: String,
    val pincode: String,
    val isDefault: Boolean
): Parcelable