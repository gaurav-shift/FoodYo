package com.example.foodyo.Presentation.AuthUI

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.util.CoilUtils.result
import com.example.foodyo.dataLayer.local.TokenManager
import com.example.foodyo.domainLayer.usecase.IsUserLoggedInUseCase
import com.example.foodyo.domainLayer.usecase.LoginUseCase
import com.example.foodyo.domainLayer.usecase.SignUpUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
        private val _authState = MutableStateFlow<Results<Any>>(Results.Idle)
        val authState = _authState.asStateFlow()

        private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
        val isLoggedIn = _isLoggedIn.asStateFlow()

    fun login(
        email: String,
        password: String
    ){
        _authState.value = Results.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginUseCase(email,password)
            if(
                result is Results.Success && result.data.data?.token != null
            ){
                tokenManager.saveToken(result.data.data!!.token)
            }
//            Log.d(
//                "JWT_TOKEN",
//                tokenManager.getToken().first() ?: "NULL"
//            )
            _authState.value = result
        }

    }
    fun signUp(
        name: String,
        email: String,
        password: String
    ){
        _authState.value = Results.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = signUpUseCase(name, email, password)
            _authState.value = result
        }
    }
    fun resetAuthState(){
        _authState.value = Results.Idle
    }

    fun checkUserSession(){
        viewModelScope.launch {

            val token = tokenManager
                .getToken()
                .first()

            _isLoggedIn.value =
                !token.isNullOrBlank()
        }
    }

}