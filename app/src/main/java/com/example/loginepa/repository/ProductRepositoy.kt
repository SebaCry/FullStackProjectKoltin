package com.example.loginepa.repository

import com.example.loginepa.data.product.Product
import com.example.loginepa.data.product.RoomProductLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val localDataSource: RoomProductLocalDataSource
){
    suspend fun insertProduct(product : Product) : Result<String> {
        val productDb = localDataSource.getProductById(product.id)

        return if (productDb != null) {
            Result.failure(Exception("Producto ya existente"))
        } else {
            localDataSource.insertProduct(product)
            Result.success("Producto insertado con éxito")
        }
    }

    suspend fun deleteProduct(product : Product) : Result<String> {
        localDataSource.deleteProduct(product)

        return Result.success("Producto eliminado correctamente")
    }

    suspend fun updateProduct(product : Product) : Result<String> {
        val productDb = localDataSource.getProductById(product.id)

        if (product.id <= 0) {
            return Result.success("Usuario no encontrado")
        }

        return if (productDb == null) {
            Result.failure(Exception("Producto no encontrado"))
        } else {
            localDataSource.updateProduct(product)
            Result.success("Producto actualizado con éxito")
        }
    }

    fun getAllProducts() : Flow<List<Product>> {
        return localDataSource.getAllProducts()
    }


}