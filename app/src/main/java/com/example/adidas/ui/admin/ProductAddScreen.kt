package com.example.adidas.ui.admin

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.adidas.ui.AppViewModelProvider
import com.example.adidas.ui.admin.viewModel.ProductDetailsViewModel
import com.example.adidas.ui.theme.AdidasTheme

@Composable
fun ProductAddScreen(
    onSaveButtonClicked: () -> Unit,
    onBackClicked:() -> Unit,
    productViewModel: ProductDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by productViewModel.uiState.collectAsState()

    AddProductContent(
        saveButtonClicked = onSaveButtonClicked,
        onBackClicked = onBackClicked
    )
}

@Composable
fun AddProductContent(
    saveButtonClicked: () -> Unit = {},
    onBackClicked:() -> Unit = {}
) {
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color.LightGray
    ) {
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
            },
            bottomBar = {
                SaveButton(
                    onSaveClick = saveButtonClicked
                )
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

                ProductAddInput(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun ProductAddInput(
    modifier: Modifier = Modifier
){
    var productPrice by remember { mutableStateOf("") }
    var nameInput by remember { mutableStateOf("") }
    //var productPic by remember { mutableStateOf("") }
    var sizeInput by remember { mutableStateOf("") }
    var stockInput by remember { mutableStateOf("") }
    var picInput by remember { mutableStateOf("") }

    val errorMessage = "Field cannot be empty!"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .verticalScroll(rememberScrollState())
    ) {
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
                .padding(top = 8.dp)
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

        //size input
        Text(
            text = "Size",
            modifier = Modifier
                .padding(top = 8.dp)
                .align(alignment = Alignment.Start)
        )
        TextField(
            label = { Text(text = "Size") },
            value = sizeInput,
            onValueChange = { sizeInput = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            supportingText = {
                if (sizeInput.isBlank()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (sizeInput.isBlank())
                    Icon(Icons.Filled.Info,"error", tint = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier
                .padding(top = 10.dp)
                .align(alignment = Alignment.Start)
        )

        //Stock quantity
        Text(
            text = "Stock",
            modifier = Modifier
                .padding(top = 8.dp)
                .align(alignment = Alignment.Start)
        )
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
            modifier = Modifier
                .padding(top = 10.dp)
                .align(alignment = Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))

        //product pic
        InsertImageButton()
    }
}

@Composable
fun InsertImageButton(){
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedImageUri = it }
    )

    val imagePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (!isGranted) {
                showDialog = true
            }
            else {
                // Launch the photo picker if permission is granted
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        }
    )

    Column {
        Text(
            text = "Product Image",
            modifier = Modifier
                .padding(top = 12.dp)
                .align(alignment = Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                imagePermissionResultLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        ){
            Row {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
                Text(
                    text = "Insert Image",
                    fontSize = 20.sp
                )
            }
        }

        AsyncImage(
            model = selectedImageUri,
            contentDescription = ""
        )

        if (showDialog) {
            PermissionDialog(
                isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                onDismiss = { showDialog = false },
                onOkClick = { showDialog = false },
                onGoToAppSettingClick = { openAppSettings(context) }
            )
        }
    }
}

@Composable
fun SaveButton(
    onSaveClick:() -> Unit
){
    //Save button
    Button(
        onClick = onSaveClick,
        Modifier.fillMaxWidth()
    ) {
        Text(text = "Save")
    }
}

fun openAppSettings(context: Context) {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    ).also {
        context.startActivity(it)
    }
}

@Preview
@Composable
fun AddProductScreenPreview() {
    AdidasTheme {
        AddProductContent(
            saveButtonClicked = { } ,
            onBackClicked = { }
        )
    }
}