package com.edsonlimadev.shopapp.data.api.response

data class ProductResponse(
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String,
    val rating: RatingResponse
)