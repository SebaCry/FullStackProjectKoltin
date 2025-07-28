package com.example.loginepa.screens.admin.users

import android.R.attr.description
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginepa.data.product.Product
import com.example.loginepa.data.user.User
import com.example.loginepa.data.user.UserRole
import com.example.loginepa.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelUser @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val users : StateFlow<List<User>> = userRepository.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val _adminUserState = MutableStateFlow(AdminUserState())
    val adminUserState : StateFlow<AdminUserState> = _adminUserState.asStateFlow()

    fun onRoleChange(role : UserRole) {
        _adminUserState.value = _adminUserState.value.copy(role = role)
    }

    fun onUsernameChange(username : String) {
        _adminUserState.value = _adminUserState.value.copy(username = username)
    }

    fun onEmailChange(email : String) {
        _adminUserState.value = _adminUserState.value.copy(email = email)
    }

    fun onPasswordChange(password : String) {
        _adminUserState.value = _adminUserState.value.copy(password = password)
    }

    fun insertUser() {
        val adminUserState = _adminUserState.value

        if (adminUserState.username.isBlank() ||
            adminUserState.email.isBlank() ||
            adminUserState.password.isBlank() ||
            adminUserState.role.toString().isBlank()) {

            _adminUserState.value = adminUserState.copy(
                errorMessage = "Ningun campo puede estar vacio"
            )
            return
        }

        val newUser = User(
            userName = adminUserState.username,
            email = adminUserState.email,
            password = adminUserState.password,
            role = adminUserState.role
        )

        viewModelScope.launch {
            userRepository.registerUser(newUser)
                .onSuccess { success ->
                    _adminUserState.value = _adminUserState.value.copy(
                        successMessage = success,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _adminUserState.value = _adminUserState.value.copy(
                        errorMessage = error.message,
                        successMessage = null
                    )
                }
        }
    }

    fun updateUser() {
        val adminUserState = _adminUserState.value

        if (adminUserState.username.isBlank() ||
            adminUserState.email.isBlank() ||
            adminUserState.password.isBlank() ||
            adminUserState.role.toString().isBlank()) {

            _adminUserState.value = adminUserState.copy(
                errorMessage = "Ningun campo puede estar vacio"
            )
            return
        }

        val updateUser = User(
            id = adminUserState.id,
            userName = adminUserState.username,
            email = adminUserState.email,
            password = adminUserState.password,
            role = adminUserState.role
        )

        viewModelScope.launch {
            userRepository.updateUser(updateUser)
                .onSuccess { success ->
                    _adminUserState.value = _adminUserState.value.copy(
                        successMessage = success,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _adminUserState.value = _adminUserState.value.copy(
                        errorMessage = error.message,
                        successMessage = null
                    )
                }
        }
    }

    fun deleteUser(user : User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
                .onSuccess { success ->
                    _adminUserState.value = _adminUserState.value.copy(
                        successMessage = success,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _adminUserState.value = _adminUserState.value.copy(
                        errorMessage = error.message,
                        successMessage = null
                    )
                }
        }
    }

    fun getUserById(id : Int) : StateFlow<User?> {
        return userRepository.getAllUsers()
            .map { users -> users.find { it.id == id } }
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }

    fun setUser(user : User) {
        _adminUserState.value = _adminUserState.value.copy(
            id = user.id,
            username = user.userName,
            email = user.email,
            password = user.password,
            role = user.role
        )
    }
}