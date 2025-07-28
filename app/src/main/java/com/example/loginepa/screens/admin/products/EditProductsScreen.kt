package com.example.loginepa.screens.admin.products

import android.R.attr.password
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun EditProductsScreen(
    navController: NavHostController,
    productId : Int,
    viewModelProduct: ViewModelProduct = hiltViewModel()
) {
    val productState by viewModelProduct.productState.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        viewModelProduct.getProductById(productId).collect { product ->
            product?.let {
                viewModelProduct.setProduct(it)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = productState.name,
            onValueChange = viewModelProduct::onNameChange,
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = productState.description,
            onValueChange = viewModelProduct::onDescriptionChange,
            maxLines = 3,
            label = { Text("Descripcion") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = productState.price.toString(),
            onValueChange = {
                val parsed = it.toIntOrNull() ?: 0
                viewModelProduct.onPriceChange(parsed)
            },
            label = { Text("Precio") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = productState.image,
            onValueChange = viewModelProduct::onImageChange,
            label = { Text("Imagen") }
        )

        Button(
            onClick = {
                viewModelProduct.editProduct()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar Producto")
        }
    }
}