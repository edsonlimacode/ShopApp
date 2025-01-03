package com.edsonlimadev.shopapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edsonlimadev.shopapp.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites WHERE userId = :userId")
    fun getAllByUserId(userId: Int): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)
}