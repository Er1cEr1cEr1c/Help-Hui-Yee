package com.example.adidas.data.order

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.adidas.data.user.UserInformation

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    val orderDate: String, // Date??
    val orderTime: String, // LocalDateTime?? Int??
    val orderStatus: String,

    val subtotal: Double,
    val shippingFee: Double,
    val pointsRedeemed: Double,
    val totalPrice: Double,
)
