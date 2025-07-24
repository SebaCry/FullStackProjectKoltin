package com.example.loginepa.screens.login

import com.example.loginepa.data.user.User

data class LoginState (
    val email : String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String?= null,
    val loginSuccess : User? = null
)