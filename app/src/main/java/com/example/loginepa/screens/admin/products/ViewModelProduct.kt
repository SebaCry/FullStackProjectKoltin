package com.example.loginepa.screens.admin.products

import android.R.attr.name
import android.R.id.message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginepa.data.product.Product
import com.example.loginepa.data.user.User
import com.example.loginepa.repository.ProductRepository
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
class ViewModelProduct @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState : StateFlow<ProductState> = _productState.asStateFlow()

    val products : StateFlow<List<Product>> = productRepository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteProduct(product : Product) {
        viewModelScope.launch {
            productRepository.deleteProduct(product)
                .onSuccess { success ->
                    _productState.value = _productState.value.copy(
                        successMessage = success,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _productState.value = _productState.value.copy(
                        errorMessage = error.message,
                        successMessage = null
                    )
                }
        }
    }

    fun onNameChange(name : String) {
        _productState.value = _productState.value.copy(name = name)
    }

    fun onDescriptionChange(description : String) {
        _productState.value = _productState.value.copy(description = description)
    }

    fun onPriceChange(price : Int) {
        _productState.value = _productState.value.copy(price = price)
    }

    fun onImageChange(image : String) {
        _productState.value = _productState.value.copy(image = image)
    }

    fun insertProduct() {
        val productState = _productState.value

        if (productState.name.isBlank() ||
            productState.price <= 0 ||
            productState.description.isBlank() ||
            productState.image.isBlank()) {

            _productState.value = productState.copy(
                errorMessage = "Ningun campo puede estar vacio"
            )
            return
        }

        val newProduct = Product(
            name = productState.name,
            description = productState.description,
            price = productState.price,
            image = productState.image
        )

        viewModelScope.launch {
            productRepository.insertProduct(newProduct)
                .onSuccess { success ->
                    _productState.value = _productState.value.copy(
                        successMessage = success,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _productState.value = _productState.value.copy(
                        errorMessage = error.message,
                        successMessage = null
                    )
                }
        }
    }

    fun editProduct() {
        val productState = _productState.value

        if (productState.name.isBlank() ||
            productState.price <= 0 ||
            productState.description.isBlank() ||
            productState.image.isBlank()) {

            _productState.value = productState.copy(
                errorMessage = "Ningun campo puede estar vacio"
            )
            return
        }

        val updateProduct = Product(
            id = productState.id,
            name = productState.name,
            description = productState.description,
            price = productState.price,
            image = productState.image
        )

        viewModelScope.launch {
            productRepository.updateProduct(updateProduct)
                .onSuccess { success ->
                    _productState.value = _productState.value.copy(
                        successMessage = success,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _productState.value = _productState.value.copy(
                        errorMessage = error.message,
                        successMessage = null
                    )
                }
        }
    }

    fun getProductById(id : Int) : StateFlow<Product?> {
        return productRepository.getAllProducts()
            .map { users -> users.find { it.id == id } }
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }

    fun setProduct(product : Product) {
        _productState.value = _productState.value.copy(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            image = product.image
        )
    }

}