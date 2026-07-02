package com.example.foodyo.dataLayer.repositoryImpl

import android.util.Log
import com.example.foodyo.dataLayer.remote.dto.address.CreateAddressRequestDto
import com.example.foodyo.dataLayer.remote.dto.address.CreateAddressResponseDto
import com.example.foodyo.dataLayer.remote.dto.address.DeleteAddressResponseDto
import com.example.foodyo.dataLayer.remote.dto.address.GetAddressesResponseDto
import com.example.foodyo.dataLayer.remote.dto.address.UpdateAddressRequestDto
import com.example.foodyo.dataLayer.remote.dto.address.UpdateAddressResponseDto
import com.example.foodyo.dataLayer.services.AddressApiService
import com.example.foodyo.domainLayer.repository.AddressRepository
import com.example.foodyo.domainLayer.util.Results
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class AddressRepositoryImpl(
    private val addressApiService: AddressApiService
): AddressRepository {
    override suspend fun createAddress(
        request: CreateAddressRequestDto
    ): Results<CreateAddressResponseDto> {

        return try {

            val response = addressApiService.client
                .post("/api/v1/addresses") {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }
                .body<CreateAddressResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(response.message)
            }

        } catch (e: Exception) {

            Log.d("ADDRESS_ERROR", e.stackTraceToString())

            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun getAddresses():
            Results<GetAddressesResponseDto> {

        return try {

            val response = addressApiService.client
                .get("/api/v1/addresses")
                .body<GetAddressesResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(response.message)
            }

        } catch (e: Exception) {

            Log.d("ADDRESS_ERROR", e.stackTraceToString())

            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun updateAddress(
        addressId: String,
        request: UpdateAddressRequestDto
    ): Results<UpdateAddressResponseDto> {

        return try {

            val response = addressApiService.client
                .put("/api/v1/addresses/$addressId") {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }
                .body<UpdateAddressResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(response.message)
            }

        } catch (e: Exception) {

            Log.d("ADDRESS_ERROR", e.stackTraceToString())

            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun deleteAddress(
        addressId: String
    ): Results<DeleteAddressResponseDto> {

        return try {

            val response = addressApiService.client
                .delete("/api/v1/addresses/$addressId")
                .body<DeleteAddressResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(response.message)
            }

        } catch (e: Exception) {

            Log.d("ADDRESS_ERROR", e.stackTraceToString())

            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

}