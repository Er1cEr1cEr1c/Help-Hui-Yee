package com.example.adidas.ui.feat_product_selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adidas.R

@Composable
fun ProductLists() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(//
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
                text = "Products",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 items per row
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(8.dp), // Add padding to provide space around the grid
        contentPadding = PaddingValues(8.dp), // Add content padding between items
        verticalArrangement = Arrangement.spacedBy(8.dp), // Add vertical spacing between items
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = 4) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        painter = painterResource(id = R.drawable.shoe_rm),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Adidas Jordan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "RM315.20",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = Color.White, // Set the background color of the icon
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .align(Alignment.End)
                            .size(48.dp),
                        content = {
                            IconButton(
                                onClick = { /*TODO*/ }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_add_24),
                                    contentDescription = null,
                                    tint = Color.Black // Set the icon color
                                )
                            }
                        }
                    )
                }
            }
        }
    }
    }
}

@Preview
@Composable
fun PreviewProductLists() {
    ProductLists()
}