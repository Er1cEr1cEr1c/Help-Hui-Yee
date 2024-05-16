package com.example.adidas.ui.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adidas.data.cart.CartEntity
import com.example.adidas.data.cart.cartList
import com.example.adidas.ui.AppViewModelProvider
import com.example.adidas.ui.admin.StatusUpdater

@Preview(showBackground = true)
@Composable
fun OrderPreview() {
    OrderDetailsContent(
        cartList = cartList,
        orderId = 1.toString(),
        orderDate = "03/04/2024",
        orderTime = "14:55",
        orderStatus = "Preparing",
        memberPoints = 150.0,
        subtotal = 2996.00,
        shippingFee = 25.00,
        redeemPoints = false,
        pointsRedeemed = 150.0,
        totalPrice = 2871.0,
        onAddressClicked = { },
        onBackButtonClicked = { },
        statusUpdater = false,
    )
}

@Composable
fun OrderDetailsScreen(
    onAddressClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
    orderViewModel: OrderDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by orderViewModel.uiState.collectAsState()

    OrderDetailsContent(
        cartList = uiState.cartList,
        orderId = uiState.orderId.toString(),
        orderDate = uiState.orderDate,
        orderTime = uiState.orderTime,
        orderStatus = uiState.orderStatus,
        memberPoints = 150.0,
        subtotal = uiState.subtotal,
        shippingFee = uiState.shippingFee,
        redeemPoints = true,
        pointsRedeemed = uiState.pointsRedeemed,
        totalPrice = uiState.totalPrice,
        onAddressClicked = onAddressClicked,
        onBackButtonClicked = onBackButtonClicked,
        statusUpdater = false
    )
}

@Composable
fun OrderDetailsContent(
    cartList: List<CartEntity>,
    orderId: String,
    orderDate: String,
    orderTime: String,
    orderStatus: String,
    memberPoints: Double,
    subtotal: Double,
    shippingFee: Double,
    redeemPoints: Boolean,
    pointsRedeemed: Double,
    totalPrice: Double,
    onAddressClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
    statusUpdater: Boolean
) {

    Scaffold(
        topBar = {
            CartAppBar(
                currentScreen = CartScreen.OrderDetails,
                canNavigateBack = true,
                navigateUp = onBackButtonClicked
            )
        },

        bottomBar = {
            if (statusUpdater) {
                Column (modifier = Modifier.padding(0.dp)){
                    PriceCard(
                        memberPoints = memberPoints,
                        subtotal = subtotal,
                        shippingFee = shippingFee,
                        redeemButton = {},
                        redeemPoints = redeemPoints,
                        pointsRedeemed = pointsRedeemed,
                        totalPrice = totalPrice,
                        backToHome = {},
                        onCheckoutButtonClicked = {},
                        enableButton = false,
                        enableDialog = true
                    )
                    Divider(color = Color.Black, thickness = 3.dp, modifier = Modifier.padding(top = 0.dp))
                    StatusUpdater()
                }
            }
            else{
                PriceCard(
                    memberPoints = memberPoints,
                    subtotal = subtotal,
                    shippingFee = shippingFee,
                    redeemButton = {},
                    redeemPoints = redeemPoints,
                    pointsRedeemed = pointsRedeemed,
                    totalPrice = totalPrice,
                    backToHome = {},
                    onCheckoutButtonClicked = {},
                    enableButton = false,
                    enableDialog = false
                )
            }
        }

    ) { innerPadding ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OrderSummary(
                id = orderId,
                date = orderDate,
                time = orderTime,
                status = orderStatus,
                onAddressClicked = onAddressClicked,
                enableAddressButton = true)

            LazyColumn {
                items(cartList) { cartItem ->
                    ShoppingCartItem(
                        cartItem = cartItem,
                        enableQuantityButton = false,
                        onIncreaseClicked = {},
                        onDecreaseClicked = {},
                        enableRemoveButton = false,
                        onRemoveItemClick = {}
                    )

                }
            }

        }
    }
}

@Composable
fun OrderSummary(
    id: String,
    date: String,
    time: String,
    status: String,
    onAddressClicked: () -> Unit,
    enableAddressButton: Boolean
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        val (orderID, orderDate, orderStatus, deliveryAdd,
            custInfo, custAdd, editIcon) = createRefs()
        val startGuideline = createGuidelineFromStart(25.dp)
        val topGuideline = createGuidelineFromTop(5.dp)
        val endGuideline = createGuidelineFromEnd(30.dp)

        Text(
            text = "Order ID : $id",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(orderID) {
                    start.linkTo(startGuideline)
                }
        )

        Text(
            text = "Order Date: $date $time",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(orderDate) {
                    start.linkTo(startGuideline)
                    top.linkTo(orderID.bottom)
                }
        )

        Text(
            text = status,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = Color(0xFF5B99E1),
            modifier = Modifier
                .constrainAs(orderStatus) {
                    end.linkTo(endGuideline)
                    top.linkTo(topGuideline)
                }
        )

        Text(
            text = "Delivery Address",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(deliveryAdd) {
                    start.linkTo(startGuideline)
                    top.linkTo(orderDate.bottom, 10.dp)
                }
        )

        Text(
            text = "I am Mario | +6012-333 4444",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(custInfo) {
                    start.linkTo(startGuideline)
                    top.linkTo(deliveryAdd.bottom)
                }
        )

        Text(
            text = "TAR UMT, Jalan Genting Klang, Setapak 53000, Kuala Lumpur",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .width(250.dp)
                .wrapContentHeight()
                .constrainAs(custAdd) {
                    start.linkTo(startGuideline)
                    top.linkTo(custInfo.bottom)
                }
        )

        if (enableAddressButton) {
            IconButton(
                onClick = onAddressClicked,
                modifier = Modifier
                    .constrainAs(editIcon) {
                        end.linkTo(endGuideline)
                        top.linkTo(deliveryAdd.top)
                    }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "Edit Delivery Info",
                )
            }
        }
    }
}