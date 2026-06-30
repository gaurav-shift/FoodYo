package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.dataLayer.remote.dto.address.UpdateAddressRequestDto
import com.example.foodyo.domainLayer.repository.AddressRepository
import javax.inject.Inject

class UpdateAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {

    suspend operator fun invoke(
        addressId: String,
        request: UpdateAddressRequestDto
    ) = repository.updateAddress(
        addressId,
        request
    )
}