package com.example.foodyo.dataLayer.repositoryImpl

import com.example.foodyo.dataLayer.remote.dto.restaurant.RestaurantListResponseDto
import com.example.foodyo.dataLayer.remote.dto.restaurant.RestaurantResponseDto
import com.example.foodyo.dataLayer.services.RestaurantApiService
import com.example.foodyo.domainLayer.repository.RestaurantRepository
import com.example.foodyo.domainLayer.util.Results
import io.ktor.client.call.body
import io.ktor.client.request.get

class RestaurantRepositoryImpl(
    private val restaurantApiService: RestaurantApiService
) : RestaurantRepository {

    override suspend fun getRestaurants():
            Results<RestaurantListResponseDto> {

        return try {

            val response = restaurantApiService.client
                .get("/api/v1/restaurants")
                .body<RestaurantListResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(
                    response.message
                )
            }

        } catch (e: Exception) {
            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun getRestaurantById(
        id: String
    ): Results<RestaurantResponseDto> {

        return try {

            val response = restaurantApiService.client
                .get("/api/v1/restaurants/$id")
                .body<RestaurantResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(
                    response.message
                )
            }

        } catch (e: Exception) {
            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun searchRestaurants(
        query: String
    ): Results<RestaurantListResponseDto> {

        return try {

            val response = restaurantApiService.client
                .get("/api/v1/restaurants/search?query=$query")
                .body<RestaurantListResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(
                    response.message
                )
            }

        } catch (e: Exception) {
            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun getRestaurantsByCity(
        city: String
    ): Results<RestaurantListResponseDto> {

        return try {

            val response = restaurantApiService.client
                .get("/api/v1/restaurants?city=$city")
                .body<RestaurantListResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(
                    response.message
                )
            }

        } catch (e: Exception) {
            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }
}