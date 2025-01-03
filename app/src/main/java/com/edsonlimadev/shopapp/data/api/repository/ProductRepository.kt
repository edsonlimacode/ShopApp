package com.edsonlimadev.shopapp.data.api.repository

import com.edsonlimadev.shopapp.data.api.response.ProductResponse
import com.edsonlimadev.shopapp.data.api.service.IProductServiceApi
import com.edsonlimadev.shopapp.domain.repository.IProductRepository
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productService: IProductServiceApi
) : IProductRepository {

    override suspend fun getProducts(): List<ProductResponse> {
        return productService.getProducts()
    }

    override suspend fun getProductsByCategory(category: String?): List<ProductResponse> {
        return productService.getProductsByCategoryName(category)
    }

    override suspend fun getProductById(productId: Int?): ProductResponse {
        return productService.getProductById(productId)
    }

}