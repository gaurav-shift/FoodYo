package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.dataLayer.remote.dto.address.CreateAddressRequestDto
import com.example.foodyo.domainLayer.repository.AddressRepository
import javax.inject.Inject

class CreateAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {

    suspend operator fun invoke(
        label: String,
        receiverName: String,
        phone: String,
        addressLine1: String,
        addressLine2: String,
        city: String,
        state: String,
        pincode: String
    ) = repository.createAddress(
        CreateAddressRequestDto(
            label = label,
            receiverName = receiverName,
            phone = phone,
            addressLine1 = addressLine1,
            addressLine2 = addressLine2,
            city = city,
            state = state,
            pincode = pincode
        )
    )
}