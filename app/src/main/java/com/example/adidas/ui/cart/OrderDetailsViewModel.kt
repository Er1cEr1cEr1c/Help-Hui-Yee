package com.example.adidas.ui.cart

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adidas.data.cart.CartEntity
import com.example.adidas.data.cart.CartRepository
import com.example.adidas.data.cart.cartList
import com.example.adidas.data.order.Order
import com.example.adidas.data.order.OrderRepository
import com.example.adidas.data.order.orderTest
import com.example.adidas.ui.cart.uhh.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class OrderDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val orderRepository: OrderRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    // Define static members or methods that belong to the class itself
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

//    private val orderId: Int = checkNotNull(savedStateHandle["0"])

    init {
//        addTestOrderToCart()
        getOrder(1)
    }

    private fun getOrder(orderId: Int) {
        viewModelScope.launch {
            orderRepository.getOrderStream(orderId).collect {order ->
                if (order != null) {
                    _uiState.update {
                        it.copy(
                            orderId = order.orderId,
                            orderDate = order.orderDate,
                            orderTime = order.orderTime,
                            orderStatus = order.orderStatus,
                            subtotal = order.subtotal,
                            shippingFee = order.shippingFee,
                            pointsRedeemed = order.pointsRedeemed,
                            totalPrice = order.totalPrice
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            cartRepository.getCart().collect {cartItems ->
                _uiState.update { it.copy(cartList = cartItems) }
            }
        }
    }

    private fun addTestOrderToCart() {
        viewModelScope.launch {
            orderTest.forEach {
                orderRepository.placeOrder(it)
            }
        }
    }
}