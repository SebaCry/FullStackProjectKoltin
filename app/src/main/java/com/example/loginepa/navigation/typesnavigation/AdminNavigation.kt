package com.example.loginepa.navigation.typesnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginepa.screens.admin.dashboard.AdminScreen
import com.example.loginepa.screens.admin.products.AddProductScreen
import com.example.loginepa.screens.admin.products.AdminProductsScreen
import com.example.loginepa.screens.admin.products.EditProductsScreen
import com.example.loginepa.screens.admin.users.AddUserScreen
import com.example.loginepa.screens.admin.users.AdminUsersScreen
import com.example.loginepa.screens.admin.users.EditUserScreen

@Composable
fun AdminNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "admin_dashboard"
    ) {
        composable("admin_dashboard") {
            AdminScreen(navController)
        }
        composable("admin_users") {
            AdminUsersScreen(navController)
        }

        composable("admin_products") {
            AdminProductsScreen(navController)
        }

        composable("add_product") {
            AddProductScreen(navController)
        }

        composable("add_user") {
            AddUserScreen(navController)
        }
        composable("edit_user/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 0
            EditUserScreen(
                userId = userId,
                navController = navController
            )
        }

        composable("edit_product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
            EditProductsScreen(
                navController = navController,
                productId = productId
            )
        }
    }
}