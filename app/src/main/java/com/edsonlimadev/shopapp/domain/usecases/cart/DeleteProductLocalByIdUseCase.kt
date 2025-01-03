package com.edsonlimadev.shopapp.domain.usecases.cart

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import com.edsonlimadev.shopapp.domain.local.ProductLocal
import com.edsonlimadev.shopapp.domain.mapper.toDomain
import javax.inject.Inject


class DeleteProductLocalByIdUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(userId: Int, productId: Int): Result<Unit> {
        return try {
            cartRepository.deleteCartItem(userId, productId)
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}