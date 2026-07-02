package com.example.foodyo.domainLayer.usecase


import com.example.foodyo.domainLayer.repository.RestaurantRepository
import javax.inject.Inject

class SearchRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    suspend operator fun invoke(
        query: String
    ) = repository.searchRestaurants(query)
}