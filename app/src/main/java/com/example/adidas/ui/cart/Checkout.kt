package com.example.adidas.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adidas.ui.AppViewModelProvider

@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    CheckoutDetailsContent(
        memberPoints = 150.0,
        subtotal = 2996.00,
        shippingFee = 25.00,
        redeemButton = {},
        redeemPoints = true,
        pointsRedeemed = 150.0,
        totalPrice = 2871.0,
        placeOrder = {},
        backToHome = {},
        onBackButtonClicked = {}
    )
}

@Composable
fun CheckoutScreen(
    placeOrder: () -> Unit,
    onBackButtonClicked: () -> Unit,
    backToHome: () -> Unit,
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by cartViewModel.uiState.collectAsState()

    CheckoutDetailsContent(
        memberPoints = uiState.memberPoints,
        subtotal = uiState.subtotal,
        shippingFee = uiState.shippingFee,
        redeemButton = remember(cartViewModel) { cartViewModel::redeemPointsButton },
        redeemPoints = uiState.redeemPoints,
        pointsRedeemed = uiState.pointsRedeemed,
        totalPrice = uiState.totalPrice,
        backToHome = backToHome,
        placeOrder = placeOrder,
        onBackButtonClicked = onBackButtonClicked
    )
}

@Composable
fun CheckoutDetailsContent(
    subtotal: Double,
    memberPoints: Double,
    shippingFee: Double,
    redeemButton: () -> Unit,
    redeemPoints: Boolean,
    pointsRedeemed: Double,
    totalPrice: Double,
    backToHome: () -> Unit,
    placeOrder: () -> Unit,
    onBackButtonClicked: () -> Unit
) {

    Scaffold (
        topBar = {
            CartAppBar(
                currentScreen = CartScreen.Checkout,
                canNavigateBack = true,
                navigateUp = onBackButtonClicked
            )
        },

        bottomBar = {
            PriceCard(
                subtotal = subtotal,
                memberPoints = memberPoints,
                shippingFee = shippingFee,
                redeemButton = redeemButton,
                redeemPoints = redeemPoints,
                pointsRedeemed = pointsRedeemed,
                totalPrice = totalPrice,
                backToHome = backToHome,
                onCheckoutButtonClicked = placeOrder,
                enableButton = false,
                enableDialog = true
            )
        }

    ) {
            innerPadding ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            CheckoutDetails()
        }
    }
}

@Composable
fun CheckoutDetails() {
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(30.dp)
            .wrapContentSize()
    ){


        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "Contact Information",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                val (mailIcon, custName, nameLabel, editIcon) = createRefs()
                val startGuideline = createGuidelineFromStart(5.dp)
                val endGuideline = createGuidelineFromEnd(0.dp)
                val topGuideline = createGuidelineFromTop(5.dp)

                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Name",
                    modifier = Modifier
                        .constrainAs(mailIcon) {
                            start.linkTo(startGuideline)
                            top.linkTo(topGuideline)
                        }
                )

                Text(
                    text = "I am Mario",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .constrainAs(custName) {
                            start.linkTo(mailIcon.end, 10.dp)
                        }
                )

                Text(
                    text = "Name",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .constrainAs(nameLabel) {
                            start.linkTo(mailIcon.end, 10.dp)
                            top.linkTo(custName.bottom)
                        }
                )

                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Back",
                    modifier = Modifier
                        .constrainAs(editIcon) {
                            end.linkTo(endGuideline)
                            top.linkTo(topGuideline)
                        }
                )

            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                val (phoneIcon, custPhoneNum, phoneNumLabel, editIcon) = createRefs()
                val startGuideline = createGuidelineFromStart(5.dp)
                val endGuideline = createGuidelineFromEnd(0.dp)
                val topGuideline = createGuidelineFromTop(5.dp)

                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone Number",
                    modifier = Modifier
                        .constrainAs(phoneIcon) {
                            start.linkTo(startGuideline)
                            top.linkTo(topGuideline)
                        }
                )

                Text(
                    text = "+6012-333 4444",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .constrainAs(custPhoneNum) {
                            start.linkTo(phoneIcon.end, 10.dp)
                        }
                )

                Text(
                    text = "Phone number",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .constrainAs(phoneNumLabel) {
                            start.linkTo(phoneIcon.end, 10.dp)
                            top.linkTo(custPhoneNum.bottom)
                        }
                )

                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Back",
                    modifier = Modifier
                        .constrainAs(editIcon) {
                            end.linkTo(endGuideline)
                            top.linkTo(topGuideline)
                        }
                )

            }

            Text(
                text = "Delivery Address",
                fontWeight = FontWeight.ExtraBold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "TAR UMT, Jalan Genting Klang, Setapak 53000, Kuala Lumpur",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .width(250.dp)
                        .wrapContentHeight()
                )


                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "Edit Address Icon",
                )
            }

            Text(
                text = "Payment Method",
                fontWeight = FontWeight.ExtraBold
            )

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                val (phoneIcon, custPhoneNum, phoneNumLabel, editIcon) = createRefs()
                val startGuideline = createGuidelineFromStart(5.dp)
                val endGuideline = createGuidelineFromEnd(0.dp)
                val topGuideline = createGuidelineFromTop(5.dp)

                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Credit Card logo",
                    modifier = Modifier
                        .constrainAs(phoneIcon) {
                            start.linkTo(startGuideline)
                            top.linkTo(topGuideline)
                        }
                )

                Text(
                    text = "Credit Card / Debit Card",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .constrainAs(custPhoneNum) {
                            start.linkTo(phoneIcon.end, 10.dp)
                        }
                )

                Text(
                    text = "**** **** 0696 4629",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .constrainAs(phoneNumLabel) {
                            start.linkTo(phoneIcon.end, 10.dp)
                            top.linkTo(custPhoneNum.bottom)
                        }
                )

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "Edit Payment Method",
                    modifier = Modifier
                        .constrainAs(editIcon) {
                            end.linkTo(endGuideline)
                            top.linkTo(topGuideline)
                        }
                )

            }
        }
    }
}