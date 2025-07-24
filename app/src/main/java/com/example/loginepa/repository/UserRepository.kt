package com.example.loginepa.repository

import com.example.loginepa.data.user.RoomUserLocalDataSource
import com.example.loginepa.data.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val localDataSource : RoomUserLocalDataSource
) {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()


    suspend fun registerUser(user: User) : Result<String> {
        val userDb = localDataSource.getUserByEmail(user.email)

        return if (userDb != null) {
            Result.failure(Exception("El usuario ya existe"))
        } else {
            localDataSource.insertUser(user)
            Result.success("Usuario registrado correctamente")
        }
    }

    suspend fun loginUser(email: String, password: String): Result<User> {
        val user = localDataSource.authenticateUser(email, password)

        return if (user != null) {
            _currentUser.value = user
            Result.success(user)
        } else {
            Result.failure(Exception("Credenciales incorrectas"))
        }

    }

    suspend fun updateUser(user: User) : Result<String> {
        val userDb = localDataSource.getUserByEmail(user.email)
        return if (userDb != null && userDb.id != user.id) {
            Result.failure(Exception("El email ya está en uso por otro usuario"))
        } else {
            localDataSource.updateUser(user)
            if (_currentUser.value?.id == user.id) {
                _currentUser.value = user
            }
            Result.success("Usuario actualizado correctamente")
        }
    }

    suspend fun deleteUser(user: User) : Result<String> {
        localDataSource.deleteUser(user)
        if (_currentUser.value?.id == user.id) {
            _currentUser.value = null // Cierra sesión si se elimina el usuario actual
        }
        return Result.success("Usuario eliminado correctamente")
    }

    fun getAllUsers() : Flow<List<User>> = localDataSource.getAllUsers()

    fun logout() {
        _currentUser.value = null
    }

    fun getCurrentUser() : User? {
        return _currentUser.value
    }

}




