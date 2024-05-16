package com.example.adidas.data.cart

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class OfflineCartRepository(private val cartDao: CartDao) : CartRepository {

    override fun getCart(): Flow<List<CartEntity>> =
        cartDao.getCart()

    override fun getCartItem(id: Int): Flow<CartEntity?> =
        cartDao.getCartItem(id)

    @WorkerThread // TO run in background (can be stopped and run later)
    override suspend fun addProductToCart(cartEntity: CartEntity) =
        cartDao.addProductToCart(cartEntity)

    @WorkerThread
    override suspend fun removeItemFromCart(id: Int) =
        cartDao.removeItemFromCart(id)

    @WorkerThread
    override suspend fun increaseCartItemCount(cartItemId: Int) =
        cartDao.increaseCartItemCount(cartItemId)

    @WorkerThread
    override suspend fun decreaseCartItemCount(cartItemId: Int) =
        cartDao.decreaseCartItemCount(cartItemId)
}