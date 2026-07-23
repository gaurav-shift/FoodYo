package com.example.foodyo.domainLayer.repository

import com.example.foodyo.dataLayer.remote.dto.menu.GetMenusResponseDto
import com.example.foodyo.dataLayer.remote.dto.menu.MenuResponseDto
import com.example.foodyo.domainLayer.util.Results

interface MenuRepository {

    suspend fun getMenusByRestaurantId(
        restaurantId: String
    ): Results<GetMenusResponseDto>

    suspend fun getMenuById(
        id: String
    ): Results<MenuResponseDto>

}