package com.example.loginepa.screens.admin.users

import com.example.loginepa.data.user.UserRole

data class AdminUserState(
    val id: Int = 0,
    val username : String = "",
    val email : String = "",
    val password : String = "",
    val role : UserRole = UserRole.CLIENT,
    val successMessage : String? =null,
    val errorMessage : String? = null,
)
