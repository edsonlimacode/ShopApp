package com.edsonlimadev.shopapp.domain.usecases.cart

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import com.edsonlimadev.shopapp.domain.local.ProductLocal
import com.edsonlimadev.shopapp.domain.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetAllCartItemsLocalUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(userId: Int): Result<Flow<List<ProductLocal?>>> {
        return try {
            val product = cartRepository.getCartItems(userId).map { it.map { it.toDomain() } }
            Result.success(product)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}