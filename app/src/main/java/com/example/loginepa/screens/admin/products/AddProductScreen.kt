package com.example.loginepa.screens.admin.products

import android.R.attr.label
import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun AddProductScreen(
    navController : NavHostController,
    viewModelProduct: ViewModelProduct = hiltViewModel()
) {
    val productState by viewModelProduct.productState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        ImageSelectorSection(
//            selectedImagePath = productState.image,
//            onImageSelected = { image ->
//                viewModelProduct.onImageChange(image)
//            }
//        )

        OutlinedTextField(
            value = productState.image,
            onValueChange = viewModelProduct::onImageChange,
            label = { Text("Imagen del producto") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Image, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = productState.name,
            onValueChange = viewModelProduct::onNameChange,
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.ShoppingCart, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = productState.description,
            onValueChange = viewModelProduct::onDescriptionChange,
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2,
            leadingIcon = {
                Icon(Icons.Default.Description, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = productState.price.toString(),
            onValueChange = {
                val parsed = it.toIntOrNull() ?: 0
                viewModelProduct.onPriceChange(parsed)
            },
            label = { Text("Hola") }
        )

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {
                viewModelProduct.insertProduct()
                navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
        ) {
            Text("Agregar Producto")
        }
    }
}