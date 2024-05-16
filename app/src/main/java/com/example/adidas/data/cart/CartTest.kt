package com.example.adidas.data.cart

import com.example.adidas.R

val cartList = listOf(
    CartEntity(
        productName = "Yeezy Boost 350 V2 Black",
        price = 899.00,
        orderQty = 1,
        size = "UK10",
        imageResourceId = R.drawable.forum_low_white,
        orderId = 1
    ),

    CartEntity(
        productName = "NMD R1 Black",
        price = 699.00,
        orderQty = 2,
        size = "UK7",
        imageResourceId = R.drawable.nmdr1_core_black,
        orderId = 1
    ),
    CartEntity(
        productName = "adidas_N_BAPE Yellow",
        price = 699.00,
        orderQty = 1,
        size = "UK8",
        imageResourceId = R.drawable.adidas_n_bape_yellow,
        orderId = 1
    )
)