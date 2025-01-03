package com.edsonlimadev.shopapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val id: Int = 0,
    val userId: Int,
    val productId: Int,
    val title: String,
    val price: Double,
    val image: String
)