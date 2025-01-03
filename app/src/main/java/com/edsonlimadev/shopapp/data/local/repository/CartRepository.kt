package com.edsonlimadev.shopapp.data.local.repository

import com.edsonlimadev.shopapp.data.local.dao.CartDao
import com.edsonlimadev.shopapp.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) : ICartRepository {
    override suspend fun addToCart(productEntity: ProductEntity) {
        cartDao.addToCart(productEntity)
    }

    override suspend fun update(productEntity: ProductEntity) {
        cartDao.update(productEntity)
    }

    override suspend fun getCartItems(userId: Int): Flow<List<ProductEntity>> {
        return cartDao.getCartItems(userId)
    }

    override suspend fun getCartItem(userId: Int, productId: Int): ProductEntity? {
        return cartDao.getCartItem(userId, productId)
    }

    override suspend fun deleteCartItem(userId: Int, productId: Int) {
        cartDao.deleteCartItem(userId, productId)
    }

    override suspend fun addProductQuantity(userId: Int, productId: Int) {
        cartDao.addProductQuantity(userId, productId)
    }

    override suspend fun removeProductQuantity(userId: Int, productId: Int) {
        cartDao.removeProductQuantity(userId, productId)
    }

    override suspend fun clearCart(userId: Int) {
        cartDao.clearCart(userId)
    }

    override suspend fun countCartItens(userId: Int): Int {
        return cartDao.countCartItens(userId)
    }
}