package com.example.loginepa.screens.admin.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Approval
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.loginepa.navigation.layouts.ViewModelNavigation
import com.example.loginepa.screens.admin.dashboard.components.ItemAdminScreen


@Composable
fun AdminScreen(
    navController : NavHostController,
    viewModelNavigation : ViewModelNavigation = hiltViewModel()
) {
    val currentUser = viewModelNavigation.currentUser.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Panel de Administrador",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Bienvenido ${currentUser.value?.userName}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ItemAdminScreen("Dashboard", Icons.Default.Dashboard, modifier = Modifier.weight(1f))
                    ItemAdminScreen("Usuarios", Icons.Default.People,modifier = Modifier.weight(1f))
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ItemAdminScreen("Productos", Icons.Default.CardTravel,modifier = Modifier.weight(1f))
                    ItemAdminScreen("Proveedores", Icons.Default.Approval,modifier = Modifier.weight(1f))
                }
            }
        }
    }
}