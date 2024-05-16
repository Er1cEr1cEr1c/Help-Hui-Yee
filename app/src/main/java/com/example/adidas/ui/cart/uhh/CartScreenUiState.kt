package com.example.adidas.ui.cart.uhh

import com.example.adidas.data.cart.CartEntity

data class CartScreenUiState(
    val cartList: List<CartEntity> = listOf(),
    val memberPoints: Double = 0.0,
    val subtotal: Double = 0.0,
    val shippingFee: Double = 0.0,
    val redeemPoints: Boolean = true,
    val pointsRedeemed: Double = 0.0,
    val totalPrice: Double = 0.0
)