package com.example.foodyo.dataLayer.remote.dto.order
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(

    val fullName: String?=null,

    val phone: String,

    val houseNo: String?=null,

    val area: String?=null,

    val landmark: String?=null,

    val city: String,

    val state: String,

    val pincode: String

)