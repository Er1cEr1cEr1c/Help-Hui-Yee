package com.example.adidas.data.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * from products WHERE productId = :id")
    fun getProduct(id: Int): Flow<Product>

    @Query("SELECT * from products ORDER BY productId ASC")
    fun getAllProducts(): Flow<List<Product>>
}