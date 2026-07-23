package com.example.foodyo.dataLayer.repositoryImpl

import com.example.foodyo.dataLayer.remote.dto.menu.GetMenusResponseDto
import com.example.foodyo.dataLayer.remote.dto.menu.MenuResponseDto
import com.example.foodyo.dataLayer.services.MenuApiService
import com.example.foodyo.domainLayer.repository.MenuRepository
import com.example.foodyo.domainLayer.util.Results
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuApiService: MenuApiService
) : MenuRepository {

    override suspend fun getMenusByRestaurantId(
        restaurantId: String
    ): Results<GetMenusResponseDto> {

        return try {

            val response = menuApiService.client
                .get("/api/v1/menus") {

                    parameter(
                        "restaurantId",
                        restaurantId
                    )

                }
                .body<GetMenusResponseDto>()

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

    override suspend fun getMenuById(
        id: String
    ): Results<MenuResponseDto> {

        return try {

            val response = menuApiService.client
                .get("/api/v1/menus/$id")
                .body<MenuResponseDto>()

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