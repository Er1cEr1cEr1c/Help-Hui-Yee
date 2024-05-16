package com.example.adidas.data.order

import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrdersStream(): Flow<List<Order>>

    fun getOrderStream(id: Int): Flow<Order?>

    suspend fun placeOrder(order: Order)

    suspend fun updateOrder(order: Order)

    suspend fun updateOrderStatus(id: Int, status: String)

    suspend fun deleteOrder(order: Order)
}