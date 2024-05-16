package com.example.adidas.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.adidas.data.cart.cartList
import com.example.adidas.ui.cart.OrderDetailsContent

@Preview(showBackground = true)
@Composable
fun PurchaseOrderUpdater() {
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
        statusUpdater = true,
    )
}

@Composable
fun StatusUpdater() {
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column( modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Order Status",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )

            MaterialButtonToggleGroup(items = listOf("Preparing", "Delivering", "Completed"))
        }
        
    }
}

@Composable
fun MaterialButtonToggleGroup(
    items: List<String>,
    onClick: (index: Int) -> Unit = {}
) {
    val cornerRadius = 0.dp

    val (selectedIndex, onIndexSelected) = remember { mutableStateOf<Int?>(null) }
    Row(
        modifier = Modifier.padding(0.dp)
    ) {
        items.forEachIndexed { index, item ->
            OutlinedButton(
                modifier = when (index) {
                    0 ->
                        Modifier
                            .offset(0.dp, 0.dp)
                            .zIndex(if (selectedIndex == index) 1f else 0f)
                            .height(35.dp)

                    else ->
                        Modifier
                            .offset((-1 * index).dp, 0.dp)
                            .zIndex(if (selectedIndex == index) 1f else 0f)
                            .height(35.dp)
                },
                onClick = {
                    onIndexSelected(index)
                    onClick(index)
                },
                shape = when (index) {
                    // left outer button
                    0 -> RoundedCornerShape(
                        topStart = cornerRadius,
                        topEnd = 0.dp,
                        bottomStart = cornerRadius,
                        bottomEnd = 0.dp
                    )
                    // right outer button
                    items.size - 1 -> RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = cornerRadius,
                        bottomStart = 0.dp,
                        bottomEnd = cornerRadius
                    )
                    // middle button
                    else -> RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                },
                border = BorderStroke(
                    1.dp, if (selectedIndex == index) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.DarkGray.copy(alpha = 0.75f)
                    }
                ),
                colors = if (selectedIndex == index) {
                    // selected colors
                    ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Black,
//                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        contentColor = Color.Black
//                        MaterialTheme.colorScheme.primary
                    )
                } else {
                    // not selected colors
                    ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
//                        MaterialTheme.colorScheme.surface,
                        contentColor = Color.White
//                        MaterialTheme.colorScheme.primary
                    )
                },
            ) {
                Text(
                    text = item,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (selectedIndex == index) {
                        Color.White
//                        MaterialTheme.colorScheme.primary
                    } else {
                           Color.Black
//                        Color.DarkGray.copy(alpha = 0.9f)
                    },
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                )
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .width(100.dp)
        ) {
            Text(text = "Save")
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .width(100.dp)
        ) {
            Text(text = "Cancel")
        }
    }
}
