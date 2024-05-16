package com.example.adidas.ui.admin.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adidas.R
import com.example.adidas.data.product.Product
import com.example.adidas.data.product.ProductRepository
import com.example.adidas.ui.admin.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val productId: Int = checkNotNull(savedStateHandle["0"])

    init{
        addProductToDB()
        //getProductDetails()
    }

    private fun addProductToDB() {
        viewModelScope.launch {
            productExample.forEach {
                productRepository.insertProduct(it)
            }
        }
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            productRepository.getAllProductsStream().collect { productItem ->
                _uiState.update {
                    it.copy(
                        productList = productItem
                    )
                }
            }
        }
    }
}

val productExample = listOf(
    Product(
        productName = "Yeezy Boost 350 V2 Black",
        price = 899.00,
        stockQuantity = 100,
        size = "UK10",
        imageResourceId = R.drawable.forum_low_white
    ),
    Product(
        productName = "NMD R1 Black",
        price = 699.00,
        stockQuantity = 150,
        size = "UK7",
        imageResourceId = R.drawable.nmdr1_core_black
    ),
    Product(
        productName = "adidas_N_BAPE Yellow",
        price = 699.00,
        stockQuantity = 150,
        size = "UK8",
        imageResourceId = R.drawable.adidas_n_bape_yellow
    ),
    Product(
        productName = "Centennial 85 Low ADV x Dre Black",
        price = 519.00,
        stockQuantity = 150,
        size = "UK8",
        imageResourceId = R.drawable.centennial_85_low_adv_x_dre_black
    ),
    Product(
        productName = "Samba OG Core Black",
        price = 569.00,
        stockQuantity = 150,
        size = "UK8",
        imageResourceId = R.drawable.samba_og_core_black
    ),
    Product(
        productName = "Forum Low White",
        price = 459.00,
        stockQuantity = 150,
        size = "UK8",
        imageResourceId = R.drawable.forum_low_white
    ),
    Product(
        productName = "Superstar XLG Nanzuka White",
        price = 699.00,
        stockQuantity = 150,
        size = "UK8",
        imageResourceId = R.drawable.superstar_xlg_nanzuka_white
    ),
)
