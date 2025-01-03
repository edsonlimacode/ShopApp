package com.edsonlimadev.shopapp.domain.usecases.cart

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import javax.inject.Inject


class CountCartItemsLocalUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(userId: Int): Result<Int> {
        return try {
            val count = cartRepository.countCartItens(userId)
            Result.success(count)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}