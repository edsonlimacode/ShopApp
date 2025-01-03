package com.edsonlimadev.shopapp.domain.usecases.favorite

import com.edsonlimadev.shopapp.data.local.repository.IFavoriteRepository
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal
import com.edsonlimadev.shopapp.domain.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllByUserIdLocalUseCase @Inject constructor(
    private val favoriteRepository: IFavoriteRepository
) {

    suspend operator fun invoke(userId: Int): Result<Flow<List<FavoriteLocal>>> {
        return try {
            val favorites =
                favoriteRepository.getAllByUserId(userId).map { it.map { it.toDomain() } }
            Result.success(favorites)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}