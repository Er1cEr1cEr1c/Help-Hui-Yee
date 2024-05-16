package com.example.adidas.data.cart

import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCart(): Flow<List<CartEntity>>

    fun getCartItem(id: Int): Flow<CartEntity?>

    suspend fun addProductToCart(cartEntity: CartEntity)

    suspend fun removeItemFromCart(id: Int)

    suspend fun increaseCartItemCount(cartItemId: Int)

    suspend fun decreaseCartItemCount(cartItemId: Int)
}