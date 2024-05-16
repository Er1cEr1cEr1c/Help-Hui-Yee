package com.example.adidas.data.order

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * from orders ORDER BY orderId ASC")
    fun getAllOrders(): Flow<List<Order>>

    @Query("SELECT * from orders WHERE orderId = :id LIMIT 1")
    fun getOrder(id: Int): Flow<Order>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun placeOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)

    @Query("UPDATE orders SET orderStatus = :status WHERE orderId == :id")
    suspend fun updateOrderStatus(id: Int, status: String)

    @Delete
    suspend fun deleteOrder(order: Order)
}