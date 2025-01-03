package com.edsonlimadev.shopapp.data.local.repository

import com.edsonlimadev.shopapp.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

   suspend fun getAllByUserId(userId: Int): Flow<List<FavoriteEntity>>
   suspend fun insert(favoriteEntity: FavoriteEntity)
   suspend fun delete(favoriteEntity: FavoriteEntity)

}