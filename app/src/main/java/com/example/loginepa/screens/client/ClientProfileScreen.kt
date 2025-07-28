package com.example.loginepa.screens.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.room.util.TableInfo
import com.example.loginepa.navigation.layouts.ViewModelNavigation
import com.example.loginepa.screens.admin.users.ViewModelUser
import com.example.loginepa.screens.client.components.ProfileInfoItem


@Composable
fun ClientProfileScreen(
    navController : NavHostController,
    viewModelNavigation: ViewModelNavigation = hiltViewModel()
) {

    val currentUser by viewModelNavigation.currentUser.collectAsStateWithLifecycle()

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("edit_user/${currentUser?.id}")
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar perfil")
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Perfil del cliente",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Bienvenido ${currentUser?.userName}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileInfoItem(
                        label = "Usuario",
                        value = currentUser?.userName ?: "No disponible"
                    )

                    ProfileInfoItem(
                        label = "Email",
                        value = currentUser?.email ?: "No disponible"
                    )
                    ProfileInfoItem(
                        label = "Contraseña",
                        value = "••••••••"
                    )
                    ProfileInfoItem(
                        label = "Rol",
                        value = currentUser?.role.toString()
                    )
                }
            }
        }

    }
}