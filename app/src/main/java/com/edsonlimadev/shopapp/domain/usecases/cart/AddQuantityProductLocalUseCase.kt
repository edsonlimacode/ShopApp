package com.edsonlimadev.shopapp.domain.usecases.cart

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import com.edsonlimadev.shopapp.domain.local.ProductLocal
import com.edsonlimadev.shopapp.domain.mapper.toEntity
import javax.inject.Inject


class AddQuantityProductLocalUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(userId: Int, productId: Int): Result<Unit> {
        return try {
            cartRepository.addProductQuantity(userId, productId)
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}