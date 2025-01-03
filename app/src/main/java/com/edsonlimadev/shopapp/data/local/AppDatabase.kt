package com.edsonlimadev.shopapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edsonlimadev.shopapp.data.local.dao.CartDao
import com.edsonlimadev.shopapp.data.local.dao.FavoriteDao
import com.edsonlimadev.shopapp.data.local.entity.FavoriteEntity
import com.edsonlimadev.shopapp.data.local.entity.ProductEntity

@Database(entities = [FavoriteEntity::class, ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun cartDao(): CartDao
}