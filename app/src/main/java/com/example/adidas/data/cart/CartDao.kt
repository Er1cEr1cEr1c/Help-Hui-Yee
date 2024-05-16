package com.example.adidas.data.cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * from cart_entity ORDER BY cartEntityId ASC")
    fun getCart(): Flow<List<CartEntity>>

    @Query("SELECT * from cart_entity WHERE cartEntityId = :id LIMIT 1")
    fun getCartItem(id: Int): Flow<CartEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToCart(cartEntity: CartEntity)

    @Query("DELETE FROM cart_entity WHERE cartEntityId == :id")
    suspend fun removeItemFromCart(id: Int)

    @Query("UPDATE cart_entity SET orderQty = orderQty + 1 WHERE cartEntityId == :cartItemId")
    suspend fun increaseCartItemCount(cartItemId: Int)

    @Query("UPDATE cart_entity SET orderQty = orderQty - 1 WHERE cartEntityId == :cartItemId")
    suspend fun decreaseCartItemCount(cartItemId: Int)


}