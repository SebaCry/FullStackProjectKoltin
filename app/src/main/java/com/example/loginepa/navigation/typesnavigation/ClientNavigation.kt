package com.example.loginepa.navigation.typesnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginepa.screens.client.ClientProfileScreen
import com.example.loginepa.screens.client.ClientScreen

@Composable
fun ClientNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "client_home"
    ) {
        composable("client_home") {
            ClientScreen()
        }
        composable("client_profile") {
            ClientProfileScreen()
        }
    }
}