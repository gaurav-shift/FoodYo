package com.example.foodyo.di

import com.example.foodyo.dataLayer.local.TokenManager
import com.example.foodyo.dataLayer.repositoryImpl.AuthRepositoryImpl
import com.example.foodyo.dataLayer.services.AuthApiService
import com.example.foodyo.domainLayer.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import com.example.foodyo.dataLayer.repositoryImpl.AddressRepositoryImpl
import com.example.foodyo.dataLayer.repositoryImpl.RestaurantRepositoryImpl
import com.example.foodyo.dataLayer.services.AddressApiService
import com.example.foodyo.dataLayer.services.RestaurantApiService
import com.example.foodyo.domainLayer.repository.AddressRepository
import com.example.foodyo.domainLayer.repository.RestaurantRepository
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthApiService() : AuthApiService{
        return AuthApiService()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService : AuthApiService
    ): AuthRepository{
        return AuthRepositoryImpl(authApiService)
    }
    @Provides
    @Singleton
    fun provideTokenManager(
        @ApplicationContext context: Context
    ) : TokenManager{
        return TokenManager(context)
    }

    @Provides
    @Singleton
    fun provideAddressApiService(
        tokenManager: TokenManager
    ): AddressApiService {
        return AddressApiService(tokenManager)
    }

    @Provides
    @Singleton
    fun provideAddressRepository(
        addressApiService: AddressApiService
    ): AddressRepository {
        return AddressRepositoryImpl(addressApiService)
    }

    @Provides
    @Singleton
    fun provideRestaurantApiService(
        tokenManager: TokenManager
    ): RestaurantApiService {
        return RestaurantApiService(tokenManager)
    }

    @Provides
    @Singleton
    fun provideRestaurantRepository(
        restaurantApiService: RestaurantApiService
    ): RestaurantRepository {
        return RestaurantRepositoryImpl(restaurantApiService)
    }



}