package com.example.adidas

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adidas.ui.admin.MenuScreen
import com.example.adidas.ui.admin.ProductAddScreen
import com.example.adidas.ui.admin.ProductEditScreen
import com.example.adidas.ui.admin.ProductManagementScreen
import com.example.adidas.ui.admin.PurchaseOrderManagementScreen
import com.example.adidas.ui.admin.StatisticScreen
import com.example.adidas.ui.admin.UserManagementScreen
import com.example.adidas.ui.theme.AdidasTheme

enum class AdminScreen(val title: String){
    Statistic(title = "Statistics Report"),
    ProductManagement(title = "Product Management"),
    ProductEdit(title = "Product Edit"),
    ProductAdd(title = "Product Add"),
    UserManagement(title = "User Management"),
    PurchaseOrderManagement(title = "Purchase Order Management"),
    PurchaseOrderUpdater(title = "Purchase Order Updater"),
    Menu(title = "")
}

@Composable
fun AdidasAdminScreen(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AdminScreen.Statistic.name
    ) {
        //Statistic Screen
        composable(route = AdminScreen.Statistic.name) {
            StatisticScreen(
                onMenuClicked = { navController.navigate(AdminScreen.Menu.name) }
            )
        }

        //Product Management Screen
        composable(route = AdminScreen.ProductManagement.name) {
            ProductManagementScreen(
                onMenuClicked = { navController.navigate(AdminScreen.Menu.name) },
                onProductCardClick = {
                    navController.navigate("${AdminScreen.ProductEdit.name} / ${it})")
                                     },
                onAddButtonClick = { navController.navigate(AdminScreen.ProductAdd.name) }
            )
        }

        //Product Edit Screen
        composable(route = AdminScreen.ProductEdit.name) {
            ProductEditScreen( onBackClicked = { navController.navigateUp() } )
        }

        //Product Add Screen
        composable(route = AdminScreen.ProductAdd.name) {
            ProductAddScreen(
                onSaveButtonClicked = { /*TODO*/ },
                onBackClicked = { navController.navigateUp() }
            )
        }

        //Purchase Order Management Screen
        composable(route = AdminScreen.PurchaseOrderManagement.name) {
            PurchaseOrderManagementScreen(
                onCardClick = {  },
                onMenuClick = { navController.navigate(AdminScreen.Menu.name) }
            )
        }

        //Purchase Order Updater Screen
        composable(route = AdminScreen.PurchaseOrderUpdater.name) {
            PurchaseOrderManagementScreen(
                onCardClick = {  },
                onMenuClick = { navController.navigate(AdminScreen.Menu.name) }
            )
        }

        //User Management Screen
        composable(route = AdminScreen.UserManagement.name) {
            UserManagementScreen(
                onMenuClicked = { navController.navigate(AdminScreen.Menu.name) }
            )
        }

        //Menu Screen
        composable(route = AdminScreen.Menu.name) {
            MenuScreen(
                onStatisticClicked = { navController.navigate(AdminScreen.Statistic.name) },
                onProductManagementClicked = { navController.navigate(AdminScreen.ProductManagement.name) },
                onPurchaseOrderManagementClicked = { navController.navigate(AdminScreen.PurchaseOrderManagement.name) },
                onUserManagementClicked = { navController.navigate(AdminScreen.UserManagement.name) },
                onSignOutClicked = { navController.navigate(AdminScreen.Statistic.name) },
                onBackClicked = { navController.navigateUp() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAppBar(
    currentScreen: AdminScreen,
    onMenuClicked:() -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentScreen.title,
                fontSize = 16.sp
            )
                },
        navigationIcon = {
            OutlinedButton(
                onClick = onMenuClicked,
                modifier = Modifier
                    .padding(12.dp)
                    .size(height = 40.dp, width = 60.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AdidasAppPreview() {
    AdidasTheme {
        AdidasAdminScreen()
    }
}