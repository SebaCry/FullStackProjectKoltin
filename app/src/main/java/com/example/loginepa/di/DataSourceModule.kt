package com.example.loginepa.di

import android.content.Context
import androidx.room.Room
import com.example.loginepa.data.AppDatabase
import com.example.loginepa.data.product.ProductDao
import com.example.loginepa.data.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context : Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesUserDao(database : AppDatabase) : UserDao {
        return database.userDao()
    }

    @Provides
    fun providesProductDao(database : AppDatabase) : ProductDao {
        return database.productDao()
    }
}