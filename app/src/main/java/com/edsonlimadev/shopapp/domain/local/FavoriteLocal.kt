package com.edsonlimadev.shopapp.domain.local

data class FavoriteLocal(
    val id: Int = 0,
    val userId: Int,
    val productId: Int,
    val title: String,
    val price: Double,
    val image: String
)