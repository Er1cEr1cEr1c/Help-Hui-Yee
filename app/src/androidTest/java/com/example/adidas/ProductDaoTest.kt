package com.example.adidas

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.adidas.data.AppDatabase
import com.example.adidas.data.product.Product
import com.example.adidas.data.product.ProductDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ProductDaoTest {
    private lateinit var productDao: ProductDao
    private lateinit var appDatabase: AppDatabase

    private var product1 = Product(
        productId = 0,
        productName = "NMD R1 Black",
        price = 699.00,
        stockQuantity = 150,
        size = "UK7",
        imageResourceId = R.drawable.nmdr1_core_black
    )

    private var product2 = Product(
        productId = 1,
        productName = "Yeezy Boost 350 V2 Black",
        price = 899.00,
        stockQuantity = 100,
        size = "UK10",
        imageResourceId = R.drawable.yeezyboost350v2_black
    )


    // So that it can run before every test
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        productDao = appDatabase.productDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    private suspend fun addOneProductToDb() {
        productDao.insertProduct(product1)
    }

    private suspend fun addTwoProductsToDb() {
        productDao.insertProduct(product1)
        productDao.insertProduct(product2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsProductIntoDB() = runBlocking {
        addOneProductToDb()
        val allProducts = productDao.getAllProducts().first()
        assertEquals(allProducts[0], product1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllProducts_returnsAllProductsFromDB() = runBlocking {
        // runBlocking - Used in testing scenarios to execute suspending functions
        // (like those that interact with coroutines) in a synchronous and blocking manner,
        addTwoProductsToDb()
        val allProducts = productDao.getAllProducts().first()
        assertEquals(allProducts[0], product1)
        assertEquals(allProducts[1], product2)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateProducts_updatesProductsInDB() = runBlocking {
        addTwoProductsToDb()

        productDao.updateProduct(
            Product(
            productId = 0,
            productName = "NMD R1 Black",
            price = 699.00,
            stockQuantity = 110,
            size = "UK7",
            imageResourceId = R.drawable.nmdr1_core_black
        )
        )
        productDao.updateProduct(
            Product(
            productId = 1,
            productName = "Yeezy Boost 350 V2 Black",
            price = 899.00,
            stockQuantity = 120,
            size = "UK10",
            imageResourceId = R.drawable.yeezyboost350v2_black
        )
        )


        val allProducts = productDao.getAllProducts().first()
        assertEquals(allProducts[0], Product(
            productId = 0,
            productName = "NMD R1 Black",
            price = 699.00,
            stockQuantity = 110,
            size = "UK7",
            imageResourceId = R.drawable.nmdr1_core_black
        )
        )
        assertEquals(allProducts[1], Product(
            productId = 1,
            productName = "Yeezy Boost 350 V2 Black",
            price = 899.00,
            stockQuantity = 120,
            size = "UK10",
            imageResourceId = R.drawable.yeezyboost350v2_black
        )
        )

    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteProducts_deletesAllProductsFromDB() = runBlocking {
        addTwoProductsToDb()
        productDao.deleteProduct(product1)
        productDao.deleteProduct(product2)
        val allProducts = productDao.getAllProducts().first()
        assertTrue(allProducts.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetProduct_returnsProductFromDB() = runBlocking {
        addOneProductToDb()
        val product = productDao.getProduct(1)
        assertEquals(product.first(), product1)
    }

}


