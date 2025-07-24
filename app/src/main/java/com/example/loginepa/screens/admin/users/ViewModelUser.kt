package com.example.loginepa.screens.admin.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginepa.data.user.User
import com.example.loginepa.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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


    fun registerUser(user: User, onResult: (Result<String>) -> Unit) {
        viewModelScope.launch {
            val result = userRepository.registerUser(user)
            onResult(result)
        }
    }

    fun updateUser(user: User, onResult: (Result<String>) -> Unit) {
        viewModelScope.launch {
            val result = userRepository.updateUser(user)
            onResult(result)
        }
    }

    fun deleteUser(user: User, onResult: (Result<String>) -> Unit = {}) {
        viewModelScope.launch {
            val result = userRepository.deleteUser(user)
            onResult(result)
        }
    }

    fun getUserById(id : Int) : StateFlow<User?> {
        return userRepository.getAllUsers()
            .map { users -> users.find { it.id == id } }
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }
}