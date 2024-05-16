package com.example.adidas.data.product

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class OfflineProductRepository(private val productDao: ProductDao) : ProductRepository{
    /**
     * Retrieve all the items from the the given data source.
     */
    override fun getAllProductsStream(): Flow<List<Product>> = productDao.getAllProducts()

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    override fun getProductStream(id: Int): Flow<Product?> = productDao.getProduct(id)

    /**
     * Insert item in the data source
     */
    @WorkerThread // TO run in background (can be stopped and run later)
    override suspend fun insertProduct(product: Product) = productDao.insertProduct(product)

    /**
     * Delete item from the data source
     */
    @WorkerThread
    override suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)

    /**
     * Update item in the data source
     */
    @WorkerThread
    override suspend fun updateProduct(product: Product) = productDao.insertProduct(product)
}