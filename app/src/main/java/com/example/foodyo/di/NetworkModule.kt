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
}