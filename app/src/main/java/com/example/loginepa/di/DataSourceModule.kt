package com.example.loginepa.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // ejemplo: agregar columna nueva
            database.execSQL("ALTER TABLE products ADD COLUMN newField TEXT NOT NULL DEFAULT ''")
        }
    }
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context : Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(MIGRATION_1_2)
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