package com.example.adidas.ui.cart.uhh

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.adidas.R

@Preview(showBackground = true)
@Composable
fun DialogPreview(){
    DialogWithImage(
        titleText = "Your Bid Is Placed Successfully",
        enableSmallText = false,
        toOrderDetails = {},
        backToShopping = {}
    )
}

@Composable
fun DialogWithImage(
    titleText: String,
    enableSmallText: Boolean,
    toOrderDetails: () -> Unit,
    backToShopping: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false)}

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .size(width = 350.dp, height = 40.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),

            ) {
            Text("Place Order")
        }


    if (showDialog) {
        Dialog(onDismissRequest = { toOrderDetails() }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = when (enableSmallText) {
                    true -> Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                        .padding(16.dp)

                    false -> Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .padding(16.dp)
                },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color.White),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.confetti),
                        contentDescription = "Confetti",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(120.dp)
                            .padding(10.dp)

                    )
                    Text(
                        text = titleText,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(170.dp)
                            .padding(10.dp)
                    )

                    if (enableSmallText) {
                        Text(
                            text = "You have gained 227 points!",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Gray,
                        )
                    }

                    Button(
                        onClick = { backToShopping() },
                        colors = ButtonDefaults.buttonColors(Color(0xFF5B99E1)),
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Back to Shopping")
                    }

                    if (enableSmallText) {
                        Text(
                            text = "Redirecting to Order Details in 5 seconds...",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier
                                .width(140.dp)
                        )
                    }
                }
            }
        }
    }
}
