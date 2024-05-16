package com.example.adidas.ui.admin

import com.example.adidas.data.product.Product

data class ProductUiState(
    val productList: List<Product> = listOf()
)