package com.example.adidas.ui.admin

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adidas.ui.theme.AdidasTheme

@Composable
fun MenuScreen(
    onStatisticClicked: () -> Unit = {},
    onProductManagementClicked: () -> Unit = {},
    onPurchaseOrderManagementClicked: () -> Unit = {},
    onUserManagementClicked: () -> Unit = {},
    onSignOutClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
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
            }
        ) { innerPadding ->

            Column(Modifier.padding(top = 70.dp)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = onStatisticClicked
                ) {
                    MenuSection(
                        title = "Statistic Reports",
                        icon = Icons.Default.Person,
                        content = "Statistic Reports"
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = onProductManagementClicked
                ) {
                    MenuSection(
                        title = "Product Management",
                        icon = Icons.Default.Person,
                        content = "Product Management"
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = onPurchaseOrderManagementClicked
                ) {
                    MenuSection(
                        title = "Purchase Order Management",
                        icon = Icons.Default.Person,
                        content = "Purchase Order Management"
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = onUserManagementClicked
                ) {
                    MenuSection(
                        title = "User Management",
                        icon = Icons.Default.Person,
                        content = "User Management"
                    )
                }
                Divider(
                    thickness = 1.dp,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = onSignOutClicked
                ) {
                    MenuSection(
                        title = "Sign Out",
                        icon = Icons.Default.Person,
                        content = "Sign Out"
                    )
                }
                Spacer(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
@Composable
fun MenuSection(
    title: String,
    icon: ImageVector,
    content: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = content,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun MenuPreview() {
    AdidasTheme {
        MenuScreen()
    }
}