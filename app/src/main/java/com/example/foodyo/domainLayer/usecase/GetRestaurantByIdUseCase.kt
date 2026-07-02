package com.example.foodyo.domainLayer.usecase


import com.example.foodyo.domainLayer.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantByIdUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    suspend operator fun invoke(
        id: String
    ) = repository.getRestaurantById(id)
}