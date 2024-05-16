package com.example.adidas.ui.feat_product_specification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adidas.R
import com.example.adidas.data.listOfShoesSizes


@Composable
fun ProductDetailsScreen(
    shoeModel: String,
    shoePrice: String,
    shoeDescription: String,
    shoeSize: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(16.dp)
                    .size(60.dp)
            ) {
                Text(
                    text = "<",
                    fontSize = 30.sp,
                    color = Color.Black
                )
            }
            Text(
                text = "Mens' Shoes",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)      //
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .weight(1f),
            painter = painterResource(id = R.drawable.shoe_rm),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(Color.White)
                .weight(1f),
        ) {
            Text(
                text = shoeModel,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "RM" + shoePrice,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = shoeDescription,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Size",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(listOfShoesSizes) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                Color.LightGray
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = it.toString()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Divider(
                thickness = 1.dp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Price: " + shoePrice,
                    fontSize = 25.sp
                )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

                ) {
                    Text(text = "Add to cart")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductDetailsScreen() {
    ProductDetailsScreen(
        shoeModel = "Adidas Jordan Max",
        shoePrice = "335.00",
        shoeDescription = "American brand of basketball shoes athletic",
        shoeSize = "55.20"
    )
}

