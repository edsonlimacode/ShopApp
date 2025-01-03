package com.edsonlimadev.shopapp.domain.local

data class ProductLocal(
    val id: Int = 0,
    val userId: Int,
    val productId: Int,
    val title: String,
    val price: Double,
    val image: String,
    val quantity: Int = 1
)