package com.example.adidas.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adidas.data.cart.CartEntity
import com.example.adidas.data.cart.CartRepository
import com.example.adidas.data.cart.cartList
import com.example.adidas.ui.cart.uhh.CartScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {
    // Define static members or methods that belong to the class itself
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val _uiState = MutableStateFlow(CartScreenUiState())
    val uiState: StateFlow<CartScreenUiState> = _uiState.asStateFlow()

    private val _tempMemberPoints = MutableStateFlow(150.0)
    val tempMemberPoints: StateFlow<Double> = _tempMemberPoints.asStateFlow()

    init {
//        addTestProductToCart()
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            tempMemberPoints.collect { points ->
                _uiState.update {
                    it.copy(memberPoints = points)
                }
            }
        }
        viewModelScope.launch {
            cartRepository.getCart().collect { cartItems ->
                _uiState.update {currentState ->
                    currentState.copy(
                        cartList = cartItems,
                        subtotal = calculateSubtotal(cartItems),
                        shippingFee = 25.00,
                        pointsRedeemed = calculatePointsRedeemed(currentState.memberPoints),
                        totalPrice = calculateTotalPrice(cartItems, currentState.memberPoints)
                    )
                }
            }
        }
    }

    private fun addTestProductToCart() {
        viewModelScope.launch {
            cartList.forEach {
                cartRepository.addProductToCart(it)
            }
        }
    }

    fun removeProductFromCart(productId: Int) {
        viewModelScope.launch {
            // Remove item from cart repository
            cartRepository.removeItemFromCart(productId)
            // Remove item from UI state
            _uiState.update { currentState ->
                val cartItems = currentState.cartList.toMutableList().apply {
                    removeIf { it.cartEntityId == productId }
                }
                currentState.copy(
                    cartList = cartItems,
                    subtotal = calculateSubtotal(cartItems),
                    shippingFee = 25.00,
                    pointsRedeemed = calculatePointsRedeemed(currentState.memberPoints),
                    totalPrice = calculateTotalPrice(cartItems, currentState.memberPoints)
                )
            }
        }
    }

    fun increaseOrderQuantity(productId: Int) {
        viewModelScope.launch {
            cartRepository.increaseCartItemCount(productId)
            getCart()
        }
    }

    fun decreaseOrderQuantity(productId: Int) {
        viewModelScope.launch {
            cartRepository.decreaseCartItemCount(productId)
            getCart()
        }
    }

    fun redeemPointsButton() {
        viewModelScope.launch {
            _uiState.update {currentState ->
                currentState.copy(
                    redeemPoints = !currentState.redeemPoints
                )
            }
        }
        viewModelScope.launch {
            _uiState.update {currentState ->
                currentState.copy(
                    pointsRedeemed = calculatePointsRedeemed(currentState.memberPoints),
                    totalPrice = calculateTotalPrice(currentState.cartList, currentState.memberPoints)
                )
            }
        }
    }

    private fun calculateSubtotal(cartList: List<CartEntity>): Double {
        var subtotal = 0.0
        cartList.forEach {
            subtotal += it.price * it.orderQty
        }
        return subtotal
    }

    private fun calculateEarnedPoints(cartItems: List<CartEntity>): Double {
        return calculateSubtotal(cartItems) * 0.01
    }

    private fun calculatePointsRedeemed(memberPoints: Double): Double {
        return when (_uiState.value.redeemPoints) {
            true -> memberPoints
            false -> 0.00
        }
    }

    private fun calculateTotalPrice(cartItems: List<CartEntity>, memberPoints: Double): Double {
        return calculateSubtotal(cartItems) + 25.00 - calculatePointsRedeemed(memberPoints)
    }
}

//    private val _cartItems = mutableStateListOf<CartItem>()
////    val cartItems: List<CartItem> get() = _cartItems
//
//    fun addToCart(product: Product, quantity: Int) {
//        val cartItem = createCartItem(product, quantity)
//        _cartItems.add(CartItem(product, quantity))
//    }
//
//    fun removeFromCart(cartItem: CartItem) {
//        _cartItems.remove(cartItem)
//    }
//
//    fun clearCart() {
//        _cartItems.clear()
//    }
//}

//// Order and OrderLine creation
//fun createOrder(cartItems: List<CartItem>, shippingFee: Double, userId: Int): Order {
//    val subtotal = cartItems.sumOf { it.product.price * it.orderQuantity }
//    val pointsRedeemed = 0.0 // You can add logic to calculate redeemed points here
//    val totalPrice = subtotal + shippingFee
//
//    val orderDate = LocalDate.now().toString() // Example: "2024-04-25"
//    val orderTime = LocalTime.now().toString() // Example: "15:30:00"
//
//    return Order(
//        orderDate = orderDate,
//        orderTime = orderTime,
//        orderStatus = "Pending", // Set default status
//
//        subtotal = subtotal,
//        shippingFee = shippingFee,
//        pointsRedeemed = pointsRedeemed,
//        totalPrice = totalPrice,
//        userId = userId
//    )
//}

//fun createOrderLines(cartItems: List<CartItem>, orderId: Int): List<OrderLine> {
//    return cartItems.map { cartItem ->
//        OrderLine(
//            orderId = orderId,
//            productId = cartItem.product.productId,
//            orderQty = cartItem.orderQuantity,
//            price = cartItem.product.price * cartItem.orderQuantity,
//            size = cartItem.product.size
//        )
//    }
//}

//// Function to create a CartItem from a selected product and quantity
//fun createCartItem(product: Product, orderQuantity: Int): CartItem {
//    return CartItem(product = product, orderQuantity = orderQuantity)
//}
//
//data class CartItem(
//    val product: Product,
//    val orderQuantity: Int,
//)



