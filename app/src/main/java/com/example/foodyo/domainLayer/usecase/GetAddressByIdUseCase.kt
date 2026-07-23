package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.AddressRepository
import javax.inject.Inject

class GetAddressByIdUseCase @Inject constructor(
    private val repository: AddressRepository
) {

    suspend operator fun invoke(
        id: String
    ) = repository.getAddressById(id)

}