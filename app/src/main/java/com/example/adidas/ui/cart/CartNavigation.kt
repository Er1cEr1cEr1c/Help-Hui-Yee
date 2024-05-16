package com.example.adidas.ui.cart

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adidas.ui.cart.uhh.DialogWithImage

enum class CartScreen(val title: String) {
    Cart(title = "Shopping Cart"),
    Checkout(title = "Checkout"),
    OrderDetails(title = "Order Details"),
    Delivery(title = "Delivery Details")
}

@Composable
fun CartNavigation(navController: NavHostController = rememberNavController()) {
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentScreen = CartScreen.valueOf(
//        backStackEntry?.destination?.route ?: CartScreen.Cart.name
//    )

    NavHost(
        navController = navController,
        startDestination = CartScreen.Cart.name,
    ) {
        composable(route = CartScreen.Cart.name) {
            ShoppingCartScreen(
                onCheckoutButtonClicked = { navController.navigate(CartScreen.Checkout.name)},
                onBackButtonClicked = {}
            )
        }

        composable(route = CartScreen.Checkout.name) {
            CheckoutScreen(
                placeOrder = {
                    navController.navigate(CartScreen.OrderDetails.name)
                 },
                onBackButtonClicked = {navController.navigateUp()},
                backToHome = {navController.navigateUp()}
            )
        }

        composable(route = CartScreen.OrderDetails.name) {
            OrderDetailsScreen(
                onAddressClicked = {navController.navigate(CartScreen.Delivery.name)},
                onBackButtonClicked = {navController.navigateUp()}
            )
        }

        composable(route = CartScreen.Delivery.name) {
            DeliveryDetailsScreen(
                onBackButtonClicked = {navController.navigateUp()},
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartAppBar(
    currentScreen: CartScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = currentScreen.title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}