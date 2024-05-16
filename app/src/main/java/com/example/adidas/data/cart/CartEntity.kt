package com.example.adidas.data.cart

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.adidas.data.order.Order

@Entity(tableName = "cart_entity",
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["orderId"],
            childColumns = ["orderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("orderId")] // Define an index for userId
)
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val cartEntityId: Int = 0,
    val productName: String,
    val orderQty: Int,
    val price: Double,
    val size: String,
    val imageResourceId: Int,
    val orderId: Int
)
