package com.example.adidas

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.adidas.data.AppDatabase
import com.example.adidas.data.order.Order
import com.example.adidas.data.order.OrderDao
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
class OrderDaoTest {
    private lateinit var orderDao: OrderDao
    private lateinit var appDatabase: AppDatabase

    private var order1 = Order(
        orderId = 0,
        orderDate = "30/01/2024",
        orderTime = "14:55",
        orderStatus = "Preparing",

        subtotal = 1000.0,
        shippingFee = 25.0,
        pointsRedeemed = 100.0,
        totalPrice = 925.0,
        userId = 0
    )

    private var order2 = Order(
        orderId = 1,
        orderDate = "01/01/2024",
        orderTime = "17:55",
        orderStatus = "Delivered",

        subtotal = 2000.0,
        shippingFee = 100.0,
        pointsRedeemed = 100.0,
        totalPrice = 2000.0,
        userId = 1
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
        orderDao = appDatabase.orderDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    private suspend fun addOneOrderToDb() {
        orderDao.placeOrder(order1)
    }

    private suspend fun addTwoOrdersToDb() {
        orderDao.placeOrder(order1)
        orderDao.placeOrder(order2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsOrderIntoDB() = runBlocking {
        addOneOrderToDb()
        val allOrders = orderDao.getAllOrders().first()
        assertEquals(allOrders[0], order1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllOrders_returnsAllOrdersFromDB() = runBlocking {
        // runBlocking - Used in testing scenarios to execute suspending functions
        // (like those that interact with coroutines) in a synchronous and blocking manner,
        addTwoOrdersToDb()
        val allOrders = orderDao.getAllOrders().first()
        assertEquals(allOrders[0], order1)
        assertEquals(allOrders[1], order2)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateOrders_updatesOrdersInDB() = runBlocking {
        addTwoOrdersToDb()

        orderDao.updateOrder(
            Order(
            orderId = 0,
            orderDate = "30/01/2024",
            orderTime = "14:55",
            orderStatus = "Preparing",

            subtotal = 1000.0,
            shippingFee = 25.0,
            pointsRedeemed = 100.0,
            totalPrice = 1925.0,
            userId = 0
        )
        )
        orderDao.updateOrder(
            Order(
            orderId = 1,
            orderDate = "01/01/2024",
            orderTime = "17:55",
            orderStatus = "Delivered",

            subtotal = 2000.0,
            shippingFee = 100.0,
            pointsRedeemed = 100.0,
            totalPrice = 3000.0,
            userId = 1
        )
        )


        val allOrders = orderDao.getAllOrders().first()
        assertEquals(allOrders[0], Order(
            orderId = 0,
            orderDate = "30/01/2024",
            orderTime = "14:55",
            orderStatus = "Preparing",

            subtotal = 1000.0,
            shippingFee = 25.0,
            pointsRedeemed = 100.0,
            totalPrice = 1925.0,
            userId = 0
        )
        )
        assertEquals(allOrders[1], Order(
            orderId = 1,
            orderDate = "01/01/2024",
            orderTime = "17:55",
            orderStatus = "Delivered",

            subtotal = 2000.0,
            shippingFee = 100.0,
            pointsRedeemed = 100.0,
            totalPrice = 3000.0,
            userId = 1
        )
        )

    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteOrders_deletesAllOrdersFromDB() = runBlocking {
        addTwoOrdersToDb()
        orderDao.deleteOrder(order1)
        orderDao.deleteOrder(order2)
        val allOrders = orderDao.getAllOrders().first()
        assertTrue(allOrders.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetOrder_returnsOrderFromDB() = runBlocking {
        addOneOrderToDb()
        val order = orderDao.getOrder(1)
        assertEquals(order.first(), order1)
    }

}


