package com.edsonlimadev.shopapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.edsonlimadev.shopapp.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(productEntity: ProductEntity)

    @Update
    suspend fun update(productEntity: ProductEntity)

    @Query("SELECT * FROM carts WHERE userId = :userId")
    fun getCartItems(userId: Int): Flow<List<ProductEntity>>

    @Query("SELECT * FROM carts WHERE userId = :userId AND productId = :productId")
    suspend fun getCartItem(userId: Int, productId: Int): ProductEntity?

    @Query("DELETE FROM carts WHERE userId = :userId AND productId = :productId")
    suspend fun deleteCartItem(userId: Int, productId: Int)

    @Query("UPDATE carts SET quantity = quantity + 1 WHERE userId = :userId AND productId = :productId")
    suspend fun addProductQuantity(userId: Int, productId: Int)

    @Query("UPDATE carts SET quantity = quantity - 1 WHERE userId = :userId AND productId = :productId AND quantity > 1")
    suspend fun removeProductQuantity(userId: Int, productId: Int)

    @Query("DELETE FROM carts WHERE userId = :userId")
    suspend fun clearCart(userId: Int)

    @Query("SELECT COUNT(*) FROM carts WHERE userId = :userId")
    suspend fun countCartItens(userId: Int): Int

}