package com.example.foodyo.dataLayer.repositoryImpl

import android.util.Log
import com.example.foodyo.dataLayer.remote.dto.auth.GetCurrentUserResponseDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignInRequestDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignInResponseDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignUpRequestDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignUpResponseDto
import com.example.foodyo.dataLayer.services.AuthApiService
import com.example.foodyo.domainLayer.repository.AuthRepository
import com.example.foodyo.domainLayer.util.Results
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,

) : AuthRepository {

    override suspend fun signIn(
        request: SignInRequestDto
    ): Results<SignInResponseDto> {

        return try {

            val response = authApiService.client
                .post("/api/v1/auth/signin") {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }
                    .body<SignInResponseDto>()

            if(response.success){
                Results.Success(response)
            }
            else {
                Results.Failure(
                    response.message ?: "Something went wrong"
                )
            }

           // Results.Success(response)

        } catch (e: Exception) {
           // Log.d("AUTH_ERROR", e.stackTraceToString())
            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun signUp(
        request: SignUpRequestDto
    ): Results<SignUpResponseDto> {

        return try {

            val response = authApiService.client
                .post("/api/v1/auth/signup") {
                    contentType(ContentType.Application.Json)
                    setBody(request)

                }
                    .body<SignUpResponseDto>()
            if(response.success){
                Results.Success(response)
            }
            else {
                Results.Failure(
                    response.message ?: "Something went wrong"
                )
            }

           // Results.Success(response)



        } catch (e: Exception) {
           Log.d("AUTH_ERROR", e.stackTraceToString())

            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override suspend fun getCurrentUser(): Results<GetCurrentUserResponseDto> {

        return try {

            val response = authApiService.client
                .get("/api/v1/auth/profile")
                .body<GetCurrentUserResponseDto>()

            if (response.success) {
                Results.Success(response)
            } else {
                Results.Failure(
                    response.message ?: "Something went wrong"
                )
            }

        } catch (e: Exception) {

            Log.d("AUTH_ERROR", e.stackTraceToString())

            Results.Failure(
                e.message ?: "Something went wrong"
            )
        }
    }

    override fun isUserLoggedIn(): Boolean {
        //TODO("Not yet implemented")
        return false
    }

}