package com.edsonlimadev.shopapp.domain.usecases.cart

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import javax.inject.Inject


class RemoveQuantityProductLocalUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(userId: Int, productId: Int): Result<Unit> {
        return try {
            cartRepository.removeProductQuantity(userId, productId)
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}