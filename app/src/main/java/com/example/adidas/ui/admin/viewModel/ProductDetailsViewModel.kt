package com.example.adidas.ui.admin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adidas.data.product.Product
import com.example.adidas.data.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel (
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductScreenUiState())
    val uiState: StateFlow<ProductScreenUiState> = _uiState.asStateFlow()

    init {
        addTestProductToCart()
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch {
            productRepository.getAllProductsStream().collect { productItems ->
                _uiState.update {
                    it.copy(
                        productList = productItems
                    )
                }
            }
        }
    }

    private fun addTestProductToCart() {
        viewModelScope.launch {
            productExample.forEach {
                productRepository.insertProduct(it)
            }
        }
    }

//    private fun addProductToCart(orderId: Int) {
//        val product = _uiState.value.product
//        if (product?.productId != null) {
//            viewModelScope.launch() {
//                cartRepository.addProductToCart(
//                    CartEntity(
//                        productName = product.productName,
//                        price = product.price,
//                        orderQty = 1,
//                        size = product.size,
//                        imageResourceId = product.imageResourceId,
//                        orderId = orderId
//                    )
//                )
//            }
//        }
//    }

    data class ProductDetailUiState(
        val product: Product? = null,
        val isProductInCart: Boolean = false
    )

    data class ProductScreenUiState(
        val productList: List<Product> = listOf()
    )
}