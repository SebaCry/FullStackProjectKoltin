package com.example.loginepa.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginepa.data.user.User
import com.example.loginepa.data.user.UserRole
import com.example.loginepa.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelRegister @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterState())

    val uiState : StateFlow<RegisterState> = _uiState.asStateFlow()

    fun onUsernameChange(username : String) {
        _uiState.value = _uiState.value.copy(username = username, errorMessage = null)
    }

    fun onEmailChange(email : String) {
        _uiState.value = _uiState.value.copy(email = email, errorMessage = null)
    }

    fun onPasswordChange(password : String) {
        _uiState.value = _uiState.value.copy(password = password, errorMessage = null)
    }

    fun onRoleChange(role : UserRole) {
        _uiState.value = _uiState.value.copy(selectedRole = role, errorMessage = null)
    }

    fun register() {
        val currentState = _uiState.value

        if (currentState.username.isBlank() ||
            currentState.email.isBlank() ||
            currentState.password.isBlank()) {
            _uiState.value = currentState.copy(errorMessage = "Completa todos los campos")
            return
        }

        if (currentState.password.length < 6 ) {
            _uiState.value = currentState.copy(errorMessage = "La contraseña debe tener mas de 6 caractéres")
            return
        }

        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)

        val newUser = User(
            userName = currentState.username,
            email = currentState.email,
            password = currentState.password,
            role = currentState.selectedRole
        )

        viewModelScope.launch {
            userRepository.registerUser(newUser)
                .onSuccess { message ->
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        successMessage = message,
                        registerSuccess = true
                    )
                }
                .onFailure { error ->
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
        }
    }


}