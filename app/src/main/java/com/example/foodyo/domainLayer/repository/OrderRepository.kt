package com.example.foodyo.domainLayer.repository

import com.example.foodyo.dataLayer.remote.dto.order.OrderDto
import com.example.foodyo.domainLayer.util.Results

interface OrderRepository {

    suspend fun createOrder(
        addressId: String
    ): Results<OrderDto>

    suspend fun getOrders(): Results<List<OrderDto>>

    suspend fun getOrderById(
        orderId: String
    ): Results<OrderDto>

}