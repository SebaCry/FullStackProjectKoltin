package com.example.loginepa.screens.register

import com.example.loginepa.data.user.UserRole

data class RegisterState(
    val username : String = "",
    val email : String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val successMessage: String? = null,
    val registerSuccess: Boolean = false
)
