package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.OrderRepository
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {

    suspend operator fun invoke(
        addressId: String
    ) = repository.createOrder(addressId)

}