package com.example.loginepa.data.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts() : Flow<List<Product>>

    @Insert
    suspend fun insertProduct(product : Product)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getProductById(id : Int) : Product?

    @Delete
    suspend fun deleteProduct(product : Product)

    @Update
    suspend fun updateProduct(product: Product)
}
