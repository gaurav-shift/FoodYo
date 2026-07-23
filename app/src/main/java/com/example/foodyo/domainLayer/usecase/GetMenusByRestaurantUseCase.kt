package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.MenuRepository
import javax.inject.Inject

class GetMenusByRestaurantUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(
        restaurantId: String
    ) = repository.getMenusByRestaurantId(restaurantId)

}