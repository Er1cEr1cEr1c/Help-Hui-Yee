package com.example.adidas.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.adidas.R
import com.example.adidas.data.product.Product
//import com.example.adidas.data.remote.service.auth.FirestoreHelper
import com.google.firebase.firestore.FirebaseFirestore

@Preview(showBackground = true)
@Composable
fun DeliveryPreview() {
    DeliveryDetailsContent(onBackButtonClicked = {})
}

@Composable
fun DeliveryDetailsScreen(
    onBackButtonClicked: () -> Unit,
) {

}

@Composable
fun DeliveryDetailsContent(
    onBackButtonClicked: () -> Unit,
) {
    val products: MutableList<Product> = mutableListOf()

    val tempCartItem = Product(
        productId = 0,
        productName = "Yeezy Boost 350 V2",
        price = 899.00,
        stockQuantity = 1,
        size = "UK10",
        imageResourceId = R.drawable.yeezyboost350v2_black
    )
    products.add(tempCartItem)

    Scaffold(
        topBar = {
            CartAppBar(
                currentScreen = CartScreen.Delivery,
                canNavigateBack = true,
                navigateUp = onBackButtonClicked
            )
        },

        bottomBar = {
//            PriceCard(onButtonClicked)
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StatusSummaryCard(tempCartItem)
            StatusCard()
        }
    }
}

@Composable
fun StatusSummaryCard(orderItem: Product) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 30.dp)

    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (shoeImage, deliveredText, deliveredDate, courierInfo) = createRefs()
            val topGuideline = createGuidelineFromTop(8.dp)
            val startGuideline = createGuidelineFromStart(8.dp)

            Image(
                painter = painterResource(orderItem.imageResourceId),
                contentDescription = "Yeezy Boost 350 V2",
                modifier = Modifier
                    .size(50.dp, 60.dp)
                    .constrainAs(shoeImage) {
                        start.linkTo(startGuideline)
                    }
            )

            Text(
                text = "Delivered on ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(deliveredText) {
                        top.linkTo(topGuideline)
                        start.linkTo(shoeImage.end, 10.dp)
                    }
            )

            Text(
                text = "Wed, 03/04/2024",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5B99E1),
                modifier = Modifier
                    .constrainAs(deliveredDate) {
                        top.linkTo(topGuideline)
                        start.linkTo(deliveredText.end)
                    }
            )

            Text(
                text = "Adidas Express (West Malaysia)",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .constrainAs(courierInfo) {
                        top.linkTo(deliveredDate.bottom)
                        start.linkTo(shoeImage.end, 10.dp)
                    }
            )
        }
    }
}

@Composable
fun StatusCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth(0.85F)
            .wrapContentHeight()
    ) {
        Text(
            text = "Order ID : 001",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 12.dp, top = 10.dp, bottom = 10.dp)
        )

        StatusDetails()
        StatusDetails()
        StatusDetails()
        StatusDetails()
        StatusDetails()
        StatusDetails()
        StatusDetails()
    }
}

@Composable
fun StatusDetails() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (statusDate, statusTime, statusIcon, statusName, statusDesc) = createRefs()
        val startGuideline = createGuidelineFromStart(12.dp)
        val topGuideline = createGuidelineFromTop(10.dp)
        val bottomGuideline = createGuidelineFromBottom(15.dp)

        Text(
            text = "01 April",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(statusDate) {
                    start.linkTo(startGuideline)
                }
        )

        Text(
            text = "14:55",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(statusTime) {
                    start.linkTo(startGuideline, 5.dp)
                    top.linkTo(statusDate.bottom)
                    bottom.linkTo(bottomGuideline)
                }
        )

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = "Edit Payment Method",
            modifier = Modifier
                .size(15.dp)
                .constrainAs(statusIcon) {
                    start.linkTo(statusDate.end, 10.dp)
                    top.linkTo(topGuideline)
                }
        )

        Text(
            text = "Order Placed",
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            modifier = Modifier
                .constrainAs(statusName) {
                    start.linkTo(statusIcon.end, 10.dp)
                }
        )

        Text(
            text = "Order has been placed",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(statusDesc) {
                    start.linkTo(statusIcon.end, 10.dp)
                    top.linkTo(statusName.bottom)
                    bottom.linkTo(bottomGuideline)
                }
        )
    }
}

//@Composable
//fun SaveButton(context: Context){
//    val firestoreHelper = FirestoreHelper(FirebaseFirestore.getInstance(), applicationContext)
//    Button(
//        onClick = {
//            FirestoreHelper.uploadDataToFirestore()
//        }
//    ) {
//        Text(text = "Save")
//    }
//}