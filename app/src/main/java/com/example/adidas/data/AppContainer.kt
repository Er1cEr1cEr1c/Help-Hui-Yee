/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.adidas.data

import android.content.Context
import com.example.adidas.data.bidding.BiddingRepository
import com.example.adidas.data.bidding.OfflineBiddingRepository
import com.example.adidas.data.cart.CartRepository
import com.example.adidas.data.cart.OfflineCartRepository
import com.example.adidas.data.order.OfflineOrderRepository
import com.example.adidas.data.order.OrderRepository
import com.example.adidas.data.product.OfflineProductRepository
import com.example.adidas.data.product.ProductRepository
import com.example.adidas.data.user.OfflineUserInformationRepository
import com.example.adidas.data.user.UserInformationRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val productRepository: ProductRepository
    val orderRepository: OrderRepository
    val cartRepository: CartRepository
    val userInformationRepository: UserInformationRepository
    val biddingRepository: BiddingRepository
}

/**
 * [AppContainer] implementation that provides instance of Repository
 */
class AppDataContainer(private val context: Context) : AppContainer {

    override val productRepository: ProductRepository by lazy {
        OfflineProductRepository(AppDatabase.getDatabase(context).productDao())
    }

    override val orderRepository: OrderRepository by lazy {
        OfflineOrderRepository(AppDatabase.getDatabase(context).orderDao())
    }

    override val cartRepository: CartRepository by lazy {
        OfflineCartRepository(AppDatabase.getDatabase(context).cartDao())
    }

    override val userInformationRepository: UserInformationRepository by lazy {
        OfflineUserInformationRepository(AppDatabase.getDatabase(context).userInformationDao())
    }

    override val biddingRepository: BiddingRepository by lazy {
        OfflineBiddingRepository(AppDatabase.getDatabase(context).biddingDao())
    }
}
