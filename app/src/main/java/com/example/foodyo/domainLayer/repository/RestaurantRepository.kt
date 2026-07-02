package com.example.foodyo.domainLayer.repository

import com.example.foodyo.dataLayer.remote.dto.restaurant.RestaurantListResponseDto
import com.example.foodyo.dataLayer.remote.dto.restaurant.RestaurantResponseDto
import com.example.foodyo.domainLayer.util.Results

interface RestaurantRepository {

    suspend fun getRestaurants():
            Results<RestaurantListResponseDto>

    suspend fun getRestaurantById(
        id: String
    ): Results<RestaurantResponseDto>

    suspend fun searchRestaurants(
        query: String
    ): Results<RestaurantListResponseDto>

    suspend fun getRestaurantsByCity(
        city: String
    ): Results<RestaurantListResponseDto>
}