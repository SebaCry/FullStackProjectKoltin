package com.example.loginepa.navigation.layouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginepa.data.user.UserRole
import com.example.loginepa.navigation.navigationData.NavigationItems
import com.example.loginepa.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val currentUser = userRepository.currentUser

    val navigationItems = currentUser.map { user ->
        when (user?.role) {
            UserRole.ADMIN -> {
                NavigationItems.adminItems
            }
            UserRole.CLIENT -> {
                NavigationItems.clientItems
            }
            else -> {
                emptyList()
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun logout() {
        userRepository.logout()
    }
}