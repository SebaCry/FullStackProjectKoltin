package com.example.loginepa.screens.admin.users

import android.R.attr.label
import android.R.attr.password
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.loginepa.data.user.User
import com.example.loginepa.data.user.UserRole

@Composable
fun AddUserScreen(
    navController: NavHostController,
    viewModel: ViewModelUser = hiltViewModel()
) {
    val adminUserState by viewModel.adminUserState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = adminUserState.username,
            onValueChange = viewModel::onUsernameChange,
            label = {Text("Usuario")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = adminUserState.email,
            onValueChange = viewModel::onEmailChange,
            label = {Text("Email")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = adminUserState.password,
            onValueChange = viewModel::onPasswordChange,
            label = {Text("Contraseña")},
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = adminUserState.role == UserRole.CLIENT,
                    onClick = {
                        viewModel.onRoleChange(UserRole.CLIENT)
                    }
                )
                Text(
                    text = "Cliente"
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = adminUserState.role == UserRole.ADMIN,
                    onClick = {
                        viewModel.onRoleChange(UserRole.ADMIN)
                    }
                )
                Text(
                    text = "Administrador"
                )
            }
        }


        Button(
            onClick = {
                viewModel.insertUser()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir Usuario")
        }

        adminUserState.successMessage?.let { success ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = success,
                color = MaterialTheme.colorScheme.primary
            )
        }

        adminUserState.errorMessage?.let { error ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}