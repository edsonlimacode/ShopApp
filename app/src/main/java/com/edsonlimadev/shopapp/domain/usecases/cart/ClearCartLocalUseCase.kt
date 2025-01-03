package com.edsonlimadev.shopapp.domain.usecases.cart

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import javax.inject.Inject


class ClearCartLocalUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(userId: Int): Result<Unit> {
        return try {
            cartRepository.clearCart(userId)
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}