package com.edsonlimadev.shopapp.domain.repository

import com.edsonlimadev.shopapp.data.api.response.ProductResponse

interface IProductRepository {

    suspend fun getProducts(): List<ProductResponse>
    suspend fun getProductsByCategory(category: String?): List<ProductResponse>
    suspend fun getProductById(productId: Int?): ProductResponse
}