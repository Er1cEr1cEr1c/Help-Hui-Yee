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

package com.example.adidas.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.adidas.AdidasApplication
import com.example.adidas.ui.UserProfileUwU.RegisterViewModel
import com.example.adidas.ui.admin.viewModel.ProductViewModel
import com.example.adidas.ui.cart.CartViewModel
import com.example.adidas.ui.cart.OrderDetailsViewModel
import com.example.adidas.ui.admin.viewModel.ProductDetailsViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            CartViewModel(adidasApplication().container.cartRepository)
        }

        initializer {
            OrderDetailsViewModel(
                this.createSavedStateHandle(),
                orderRepository = adidasApplication().container.orderRepository,
                cartRepository = adidasApplication().container.cartRepository,
            )
        }

        initializer {
            ProductDetailsViewModel(
                //this.createSavedStateHandle(),
                adidasApplication().container.productRepository
            )
        }

        // TESTING
        initializer {
            ProductDetailsViewModel(adidasApplication().container.productRepository)
        }

        //TESTING
        initializer {
            RegisterViewModel(adidasApplication().container.userInformationRepository)
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [AdidasApplication].
 */
fun CreationExtras.adidasApplication(): AdidasApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as AdidasApplication)
