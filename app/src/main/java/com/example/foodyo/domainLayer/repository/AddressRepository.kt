package com.example.foodyo.domainLayer.repository

import com.example.foodyo.dataLayer.remote.dto.address.CreateAddressRequestDto
import com.example.foodyo.dataLayer.remote.dto.address.CreateAddressResponseDto
import com.example.foodyo.dataLayer.remote.dto.address.DeleteAddressResponseDto
import com.example.foodyo.dataLayer.remote.dto.address.GetAddressesResponseDto
import com.example.foodyo.dataLayer.remote.dto.address.UpdateAddressRequestDto
import com.example.foodyo.dataLayer.remote.dto.address.UpdateAddressResponseDto
import com.example.foodyo.domainLayer.util.Results

interface AddressRepository {

    suspend fun createAddress(
        request: CreateAddressRequestDto
    ): Results<CreateAddressResponseDto>

    suspend fun getAddresses():
            Results<GetAddressesResponseDto>

    suspend fun updateAddress(
        addressId: String,
        request: UpdateAddressRequestDto
    ): Results<UpdateAddressResponseDto>

    suspend fun deleteAddress(
        addressId: String
    ): Results<DeleteAddressResponseDto>

}