package com.example.adidas.ui.cart.uhh

import com.example.adidas.data.cart.CartEntity

data class OrderUiState(
    val orderId: Int = 0,
    val orderDate: String = "",
    val orderTime: String = "",
    val orderStatus: String = "",
    val cartList: List<CartEntity> = listOf(),

    val subtotal: Double = 0.0,
    val shippingFee: Double = 0.0,
    val redeemPoints: Boolean = true,
    val pointsRedeemed: Double = 0.0,
    val totalPrice: Double = 0.0,
)