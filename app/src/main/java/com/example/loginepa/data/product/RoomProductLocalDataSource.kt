package com.example.loginepa.data.product

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {

    suspend fun insertProduct(product: Product) {
        return productDao.insertProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        return productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product : Product) {
        return productDao.deleteProduct(product)
    }


    fun getAllProducts() : Flow<List<Product>> {
        return productDao.getAllProducts()
    }

    suspend fun getProductById(id : Int) : Product? {
        return productDao.getProductById(id)
    }

}