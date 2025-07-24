package com.example.loginepa.data.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String,
    val description : String,
    val price : Int,
    val image : String
)
