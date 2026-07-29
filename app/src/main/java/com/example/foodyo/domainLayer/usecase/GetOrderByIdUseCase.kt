package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.OrderRepository
import javax.inject.Inject

class GetOrderByIdUseCase @Inject constructor(
    private val repository: OrderRepository
) {

    suspend operator fun invoke(
        orderId: String
    ) = repository.getOrderById(orderId)

}