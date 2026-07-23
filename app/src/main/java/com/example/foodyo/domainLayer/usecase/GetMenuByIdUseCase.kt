package com.example.foodyo.domainLayer.usecase


import com.example.foodyo.domainLayer.repository.MenuRepository
import javax.inject.Inject

class GetMenuByIdUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(
        id: String
    ) = repository.getMenuById(id)

}