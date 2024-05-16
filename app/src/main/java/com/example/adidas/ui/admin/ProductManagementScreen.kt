package com.example.adidas.ui.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adidas.AdminScreen
import com.example.adidas.AdminAppBar
import com.example.adidas.data.product.Product
import com.example.adidas.ui.AppViewModelProvider
import com.example.adidas.ui.admin.viewModel.productExample
import com.example.adidas.ui.admin.viewModel.ProductDetailsViewModel

import com.example.adidas.ui.theme.AdidasTheme

@Composable
fun ProductManagementScreen(
    onMenuClicked:() -> Unit,
    onProductCardClick:(Int) -> Unit,
    onAddButtonClick:() -> Unit,
    productViewModel: ProductDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val uiState by productViewModel.uiState.collectAsState()

    ProductManagementContent(
        productList = uiState.productList,
        onProductCardClick = onProductCardClick,
        onAddButtonClick = onAddButtonClick,
        onMenuClicked = onMenuClicked
    )
}

@Composable
fun ProductManagementContent(
    productList: List<Product>,
    onMenuClicked:() -> Unit,
    onProductCardClick:(Int) -> Unit,
    onAddButtonClick:() -> Unit
){
    Surface {
        Scaffold(
            topBar = {
                AdminAppBar(
                    currentScreen = AdminScreen.ProductManagement,
                    onMenuClicked = onMenuClicked
                )
            },

            bottomBar = {
                AddProductButton(onAddButtonClick)
            }
        ) { innerPadding ->

            Column (
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(20.dp))

                if(productList.isEmpty()){
                    Text(
                        text = "There is no item in the store!",
                        textAlign = TextAlign.Center
                    )
                }
                else{
                    ProductList(
                        productList = productList,
                        onProductCardClick = { onProductCardClick(it.productId) })
                }
            }
        }
    }
}

@Composable
fun ProductList(
    productList: List<Product>,
    onProductCardClick:(Product) -> Unit
){
    LazyColumn {
        items(items = productList){item ->
            ProductCard(
                product = item,
                modifier = Modifier
                    .clickable { onProductCardClick(item) }
                    .size(width = 360.dp, height = 120.dp),
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    modifier:Modifier = Modifier
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(product.imageResourceId),
                contentDescription = "Yeezy Boost 350 V2",
                modifier = Modifier
                    .fillMaxHeight()
                    .size(120.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = product.productName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )

                Text(
                    text = "RM ${product.price}",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 3.dp)
                )

                Text(
                    text = "Stock Available: ${product.stockQuantity}",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = product.size,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(4.dp)
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "",
                    Modifier.padding(bottom = 8.dp)
                )
            }
        }  
    }
}

@Composable
fun AddProductButton(
    onButtonClick:() -> Unit
){
    Button(
        onClick = onButtonClick ,
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Text(text = "Add New Item")
    }
}

@Preview
@Composable
fun ProductManagementPreview() {
    AdidasTheme {
        ProductManagementContent(
            productList = productExample,
            onMenuClicked = {},
            onAddButtonClick = {},
            onProductCardClick = {})
    }
}