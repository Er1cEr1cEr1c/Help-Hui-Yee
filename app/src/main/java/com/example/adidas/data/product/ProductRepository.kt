package com.example.adidas.data.product

import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProductsStream(): Flow<List<Product>>

    fun getProductStream(id: Int): Flow<Product?>

    suspend fun insertProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun updateProduct(product: Product)
}