package com.example.foodyo.dataLayer.repositoryImpl


import com.example.foodyo.dataLayer.remote.dto.order.CreateOrderRequestDto
import com.example.foodyo.dataLayer.remote.dto.order.CreateOrderResponseDto
import com.example.foodyo.dataLayer.remote.dto.order.GetOrderResponseDto
import com.example.foodyo.dataLayer.remote.dto.order.GetOrdersResponseDto
import com.example.foodyo.dataLayer.remote.dto.order.OrderDto
import com.example.foodyo.dataLayer.services.OrderApiService
import com.example.foodyo.domainLayer.repository.OrderRepository
import com.example.foodyo.domainLayer.util.Results
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class OrderRepositoryImpl(
    private val orderApiService: OrderApiService
) : OrderRepository {

    override suspend fun createOrder(
        addressId: String
    ): Results<OrderDto> {

        return try {

            val response =
                orderApiService.client.post("/api/v1/orders") {

                    contentType(ContentType.Application.Json)

                    setBody(
                        CreateOrderRequestDto(addressId)
                    )

                }.body<CreateOrderResponseDto>()

            if (response.success && response.data != null) {

                Results.Success(response.data)

            } else {

                Results.Failure(response.message)

            }

        } catch (e: Exception) {

            Results.Failure(
                e.message ?: "Something went wrong"
            )

        }

    }

    override suspend fun getOrders(): Results<List<OrderDto>> {

        return try {

            val response =
                orderApiService.client
                    .get("/api/v1/orders")
                    .body<GetOrdersResponseDto>()

            if (response.success) {

                Results.Success(response.data)

            } else {

                Results.Failure(response.message)

            }

        } catch (e: Exception) {

            Results.Failure(
                e.message ?: "Something went wrong"
            )

        }

    }

    override suspend fun getOrderById(
        orderId: String
    ): Results<OrderDto> {

        return try {

            val response =
                orderApiService.client
                    .get("/api/v1/orders/$orderId")
                    .body<GetOrderResponseDto>()

            if (response.success && response.data != null) {

                Results.Success(response.data)

            } else {

                Results.Failure(response.message)

            }

        } catch (e: Exception) {

            Results.Failure(
                e.message ?: "Something went wrong"
            )

        }

    }

}