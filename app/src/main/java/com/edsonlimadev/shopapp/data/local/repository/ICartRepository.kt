package com.edsonlimadev.shopapp.data.local.repository

import com.edsonlimadev.shopapp.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ICartRepository {

    suspend fun addToCart(productEntity: ProductEntity)
    suspend fun update(productEntity: ProductEntity)
    suspend fun getCartItems(userId: Int): Flow<List<ProductEntity>>
    suspend fun getCartItem(userId: Int, productId: Int): ProductEntity?
    suspend fun deleteCartItem(userId: Int, productId: Int)
    suspend fun addProductQuantity(userId: Int, productId: Int)
    suspend fun removeProductQuantity(userId: Int, productId: Int)
    suspend fun clearCart(userId: Int)
    suspend fun countCartItens(userId: Int): Int
}