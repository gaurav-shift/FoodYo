package com.example.foodyo.dataLayer.repositoryImpl

import android.util.Log
import com.example.foodyo.dataLayer.remote.dto.cart.AddToCartRequestDto
import com.example.foodyo.dataLayer.remote.dto.cart.CartResponseDto
import com.example.foodyo.dataLayer.remote.dto.cart.UpdateCartRequestDto
import com.example.foodyo.dataLayer.services.CartApiService
import com.example.foodyo.domainLayer.repository.CartRepository
import com.example.foodyo.domainLayer.util.Results
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartApiService: CartApiService
) : CartRepository {
    override suspend fun addToCart(
        menuId: String
    ): Results<CartResponseDto> {

      //  Log.d("CART_DEBUG", "Adding menuId = $menuId")

        return try {

            val response = cartApiService.client
                .post("/api/v1/cart/items") {

                    contentType(ContentType.Application.Json)

                    setBody(
                        AddToCartRequestDto(
                            menuId = menuId
                        )
                    )

                }
                .body<CartResponseDto>()
          //  Log.d("CART_DEBUG", "Response = $response")

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(response.message)
            }
        } catch (e: Exception) {
           // Log.e("CART_DEBUG", "POST FAILED", e)
            Results.Failure(
                e.message ?: "Something went wrong"
            )

        }

    }

    override suspend fun getCart(): Results<CartResponseDto> {

        return try {

            val response = cartApiService.client
                .get("/api/v1/cart")
                .body<CartResponseDto>()

            if (response.success) {

                Results.Success(response)

            } else {

                Results.Failure(response.message)

            }

        } catch (e: Exception) {

            Results.Failure(
                e.message ?: "Something went wrong"
            )

        }

    }

    override suspend fun updateCartItemQuantity(
        menuId: String,
        quantity: Int
    ): Results<CartResponseDto> {

        return try {

            val response = cartApiService.client
                .patch("/api/v1/cart/items/$menuId") {

                    contentType(ContentType.Application.Json)

                    setBody(
                        UpdateCartRequestDto(
                            quantity = quantity
                        )
                    )

                }
                .body<CartResponseDto>()

            if (response.success) {

                Results.Success(response)

            } else {

                Results.Failure(response.message)

            }

        } catch (e: Exception) {

            Results.Failure(
                e.message ?: "Something went wrong"
            )

        }

    }

    override suspend fun removeCartItem(
        menuId: String
    ): Results<CartResponseDto> {

        return try {

            val response = cartApiService.client
                .delete("/api/v1/cart/items/$menuId")
                .body<CartResponseDto>()


            if (response.success) {

                Results.Success(response)

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