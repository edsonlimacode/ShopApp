package com.edsonlimadev.shopapp.domain.usecases.favorite

import com.edsonlimadev.shopapp.data.local.repository.IFavoriteRepository
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal
import com.edsonlimadev.shopapp.domain.mapper.toEntity
import javax.inject.Inject

class DeleteFavoriteLocalUseCase @Inject constructor(
    private val favoriteRepository: IFavoriteRepository
) {

    suspend operator fun invoke(favoriteLocal: FavoriteLocal): Result<Unit> {
        return try {
            favoriteRepository.delete(favoriteLocal.toEntity())
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}