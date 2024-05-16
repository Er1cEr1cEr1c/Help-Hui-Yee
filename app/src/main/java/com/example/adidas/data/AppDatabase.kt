package com.example.adidas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adidas.data.bidding.Bidding
import com.example.adidas.data.bidding.BiddingDao
import com.example.adidas.data.cart.CartDao
import com.example.adidas.data.cart.CartEntity
import com.example.adidas.data.order.Order
import com.example.adidas.data.order.OrderDao
import com.example.adidas.data.product.Product
import com.example.adidas.data.product.ProductDao
import com.example.adidas.data.user.UserInformation
import com.example.adidas.data.user.UserInformationDao

@Database(entities = [Product::class, UserInformation::class, Order::class, CartEntity::class, Bidding::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun userInformationDao(): UserInformationDao
    abstract fun orderDao(): OrderDao
    abstract fun cartDao(): CartDao
    abstract fun biddingDao(): BiddingDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "adidas_database")
                    .build().also { Instance = it }
            }
        }
    }
}
