package com.example.loginepa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loginepa.data.user.UserRole
import com.example.loginepa.navigation.layouts.NavigationViewModel
import com.example.loginepa.navigation.layouts.AdminNavigationLayout
import com.example.loginepa.navigation.layouts.ClientNavigationLayout
import com.example.loginepa.navigation.typesnavigation.AuthNavigation

@Composable
fun NavigationRoot() {
    val navigationViewModel : NavigationViewModel = hiltViewModel()
    val currentUser by navigationViewModel.currentUser.collectAsStateWithLifecycle()


    when {
        currentUser == null -> {
            AuthNavigation()
        }
        currentUser?.role == UserRole.CLIENT -> {
            ClientNavigationLayout(navigationViewModel = navigationViewModel)
        }
        currentUser?.role == UserRole.ADMIN -> {
            AdminNavigationLayout(navigationViewModel = navigationViewModel)
        }
    }
}