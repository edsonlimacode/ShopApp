package com.edsonlimadev.shopapp.domain.usecases.cart

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import com.edsonlimadev.shopapp.domain.local.ProductLocal
import com.edsonlimadev.shopapp.domain.mapper.toEntity
import javax.inject.Inject


class UpdateCartLocalUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(productLocal: ProductLocal): Result<Unit> {
        return try {
            cartRepository.update(productLocal.toEntity())
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}