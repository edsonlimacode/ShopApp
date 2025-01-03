package com.edsonlimadev.shopapp.data.local.repository

import com.edsonlimadev.shopapp.data.local.dao.FavoriteDao
import com.edsonlimadev.shopapp.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) : IFavoriteRepository {

    override suspend fun getAllByUserId(userId: Int): Flow<List<FavoriteEntity>> {
        return favoriteDao.getAllByUserId(userId)
    }

    override suspend fun insert(favoriteEntity: FavoriteEntity) {
        favoriteDao.insert(favoriteEntity)
    }

    override suspend fun delete(favoriteEntity: FavoriteEntity) {
        favoriteDao.delete(favoriteEntity)
    }

}