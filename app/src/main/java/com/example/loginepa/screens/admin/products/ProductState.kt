package com.example.loginepa.screens.admin.products

data class ProductState(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val price: Int = 0,
    val image: String = "",
    val successMessage : String? =null,
    val errorMessage : String? = null,
)
