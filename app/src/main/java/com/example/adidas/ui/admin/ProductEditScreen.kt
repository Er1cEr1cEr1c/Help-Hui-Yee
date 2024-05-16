package com.example.adidas.ui.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adidas.data.product.Product
import com.example.adidas.ui.AppViewModelProvider
import com.example.adidas.ui.admin.viewModel.productExample
import com.example.adidas.ui.admin.viewModel.ProductDetailsViewModel
import com.example.adidas.ui.theme.AdidasTheme

@Composable
fun ProductEditScreen(
    onBackClicked:() -> Unit,
    productViewModel: ProductDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by productViewModel.uiState.collectAsState()

    ProductEditContent(
        productList = uiState.productList,
        onBackClicked = onBackClicked
    )
}

@Composable
fun ProductEditContent(
    productList: List<Product>,
    onBackClicked:() -> Unit = {}
) {
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color.LightGray)
    {
        Scaffold(
            topBar = {
                OutlinedButton(
                    onClick = onBackClicked,
                    modifier = Modifier
                        .padding(12.dp)
                        .size(height = 40.dp, width = 60.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
        ) { innerPadding -> Modifier.padding(4.dp)

            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp)
                    .safeDrawingPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                LazyColumn {
                    items(productList) { item ->
                        ProductEditDetails(
                            modifier = Modifier.padding(innerPadding),
                            product = item,
                            onDelete = {},
                            onSave = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductEditDetails(
    modifier: Modifier = Modifier,
    product: Product,
    onDelete:() -> Unit,
    onSave:() -> Unit
){
    var productPrice by remember { mutableStateOf(product.price.toString()) }
    var nameInput by remember { mutableStateOf(product.productName) }
    //var productPic by remember { mutableStateOf("") }
    val sizeInput by remember { mutableStateOf(product.size) }
    var stockInput by remember { mutableStateOf(product.stockQuantity.toString()) }

    val errorMessage = "Field cannot be empty!"
    var deleteConfirmation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        //product pic
        Text(
            text = "Product Image",
            modifier = Modifier
                .padding(top = 12.dp)
                .align(alignment = Alignment.Start)
        )
        Image(
            painterResource(product.imageResourceId),
            contentDescription = "Image",
            Modifier.size(width = 150.dp, height = 150.dp)
        )

        //product name
        Text(
            text = "Product Name",
            modifier = Modifier
                .padding(top = 12.dp)
                .align(alignment = Alignment.Start)
        )
        TextField(
            label = { Text(text = "Name") },
            value = nameInput,
            onValueChange = { nameInput = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = {
                if (nameInput.isBlank()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (nameInput.isBlank())
                    Icon(Icons.Filled.Info,"error", tint = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier
                .padding(top = 10.dp)
                .align(alignment = Alignment.Start)
        )

        //product prize
        Text(
            text = "Price (RM)",
            modifier = Modifier
                .padding(top = 4.dp)
                .align(alignment = Alignment.Start)
        )
        TextField(
            label = { Text(text = "Price (RM)") },
            value = productPrice,
            onValueChange = { productPrice = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            supportingText = {
                if (productPrice.isBlank()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (productPrice.isBlank())
                    Icon(Icons.Filled.Info,"error", tint = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier
                .padding(top = 10.dp)
                .align(alignment = Alignment.Start)
        )

        //Stock quantity
        Column {
            Text(
                text = "Size: $sizeInput",
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                label = { Text(text = "Quantity") },
                value = stockInput,
                onValueChange = { stockInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                supportingText = {
                    if (stockInput.isBlank()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (stockInput.isBlank())
                        Icon(Icons.Filled.Info,"error", tint = MaterialTheme.colorScheme.error)
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row (
            Modifier.fillMaxWidth()
        ) {
            //Save button
            Button(
                onClick = onSave,
                Modifier.width(160.dp)
            ) {
                Text(text = "Save")
            }

            Spacer(modifier = Modifier.width(30.dp))

            //Cancel Button
            Button(
                onClick = { deleteConfirmation = true },
                Modifier.width(160.dp)
            ) {
                Text(text = "Delete")
            }

            if (deleteConfirmation){
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        deleteConfirmation = false
                        onDelete()
                    },
                    onDeleteCancel = { deleteConfirmation = false })
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm:() -> Unit,
    onDeleteCancel:() -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        title = { Text(text = "") },
        text = { Text(text = "") },
        onDismissRequest = { /*TODO*/ },
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}

@Preview
@Composable
fun ProductManagementScreenPreview() {
    AdidasTheme {
        ProductEditContent(
            productList = productExample,
            onBackClicked = { }
        )
    }
}