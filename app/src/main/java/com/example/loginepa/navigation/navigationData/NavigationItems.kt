package com.example.loginepa.navigation.navigationData

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person

object NavigationItems {
    val clientItems = listOf(
        NavigationItem("Inicio", Icons.Default.Home, "client_home"),
        NavigationItem("Perfil", Icons.Default.Person, "client_profile"),
        NavigationItem("Productos", Icons.Default.CardTravel, "client_products")
    )

    val adminItems = listOf(
        NavigationItem("Dashboard", Icons.Default.Dashboard, "admin_dashboard"),
        NavigationItem("Usuarios", Icons.Default.People, "admin_users"),
        NavigationItem("Productos", Icons.Default.CardTravel, "admin_products")
    )
}