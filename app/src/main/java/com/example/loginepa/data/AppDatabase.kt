package com.example.loginepa.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.loginepa.data.product.ProductDao
import com.example.loginepa.data.user.UserDao
import com.example.loginepa.data.product.Product
import com.example.loginepa.data.user.User
import com.example.loginepa.data.user.UserRole

@Database(entities = [User::class, Product::class], version = 2)
@TypeConverters
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun productDao() : ProductDao
}

class UserRoleConverter {
    @TypeConverter
    fun fromUserRole(role : UserRole) : String {
        return role.name
    }

    @TypeConverter
    fun toUserRole(role : String) : UserRole {
        return UserRole.valueOf(role)
    }
}