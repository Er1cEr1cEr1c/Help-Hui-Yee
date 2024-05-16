package com.example.adidas.data.bidding

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.adidas.data.product.Product
import com.example.adidas.data.user.UserInformation

@Entity(tableName = "bidding",
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["productId"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserInformation::class,
            parentColumns = ["userId"],
            childColumns = ["winnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("productId"),
        Index("winnerId")
    ]
)

data class Bidding(
    @PrimaryKey(autoGenerate = true)
    val biddingId: Int = 0,
    val productId: Int,
    val startingPrice: Double,
    val currentPrice: Double,
    val winnerId: Int,
)