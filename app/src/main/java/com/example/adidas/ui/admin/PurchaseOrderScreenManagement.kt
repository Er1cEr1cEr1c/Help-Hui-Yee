package com.example.adidas.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.adidas.AdminAppBar
import com.example.adidas.AdminScreen
import com.example.adidas.ui.theme.AdidasTheme

@Composable
fun PurchaseOrderManagementScreen(
    onCardClick:() -> Unit = {},
    onMenuClick:() -> Unit = {}
){
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ){
        Scaffold(
            topBar = {
                AdminAppBar(
                    currentScreen = AdminScreen.PurchaseOrderManagement,
                    onMenuClicked = onMenuClick
                )
            }
        ) { innerPadding ->

            Column (Modifier.padding(innerPadding)) {
                PurchaseOrderBody(onCardClick = onCardClick)
                PurchaseOrderBody(onCardClick = onCardClick)
            }
        }
    }
}

@Composable
fun PurchaseOrderBody(
    onCardClick:() -> Unit
){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable(onClick = onCardClick)
    ) {
        PurchaseOrderDetail(onClick = onCardClick)
    }
}

@Composable
fun PurchaseOrderDetail(
    onClick:() -> Unit
){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clickable(onClick = onClick)
    ) {
        val (orderID, orderDate, orderStatus, deliveryAdd,
            custInfo, custAdd, editIcon) = createRefs()
        val startGuideline = createGuidelineFromStart(25.dp)
        val topGuideline = createGuidelineFromTop(5.dp)
        val endGuideline = createGuidelineFromEnd(30.dp)

        Text(
            text = "Order ID : 001",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(orderID) {
                    start.linkTo(startGuideline)
                }
        )

        Text(
            text = "Order Date: 03/04/2024 14:55",
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
            text = "Preparing",
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
                .size(width = 250.dp, height = 50.dp)
                .constrainAs(custAdd) {
                    start.linkTo(startGuideline)
                    top.linkTo(custInfo.bottom)
                }
        )

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = "Edit Delivery Info",
            modifier = Modifier
                .constrainAs(editIcon) {
                    end.linkTo(endGuideline)
                    top.linkTo(deliveryAdd.bottom)
                }
        )
    }
}

@Preview
@Composable
fun PurchaseOrderManagementScreenPreview() {
    AdidasTheme {
        PurchaseOrderManagementScreen()
    }
}