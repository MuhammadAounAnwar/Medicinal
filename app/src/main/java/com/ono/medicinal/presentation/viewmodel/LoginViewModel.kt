package com.ono.medicinal.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ono.medicinal.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoggedIn by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun loginUser(onLoginSuccess: (String) -> Unit) {
        viewModelScope.launch {
            val result = loginUseCase.execute(username, password)
            if (result.isSuccess) {
                isLoggedIn = true
                onLoginSuccess(username)
            } else {
                errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
            }
        }
    }
}
