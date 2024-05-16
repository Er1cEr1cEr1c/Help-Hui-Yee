package com.example.adidas.data.order

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class OfflineOrderRepository(private val orderDao: OrderDao) : OrderRepository{

    override fun getAllOrdersStream(): Flow<List<Order>> = orderDao.getAllOrders()

    override fun getOrderStream(id: Int): Flow<Order?> = orderDao.getOrder(id)

    @WorkerThread // TO run in background (can be stopped and run later)
    override suspend fun placeOrder(order: Order) = orderDao.placeOrder(order)

    @WorkerThread
    override suspend fun updateOrder(order: Order) = orderDao.updateOrder(order)

    @WorkerThread
    override suspend fun updateOrderStatus(id: Int, status: String) = orderDao.updateOrderStatus(id, status)

    @WorkerThread
    override suspend fun deleteOrder(order: Order) = orderDao.deleteOrder(order)
}