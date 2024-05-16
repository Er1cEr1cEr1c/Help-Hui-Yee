package com.example.adidas.ui.feat_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adidas.data.listOfProductCategories
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.sayHi()

    LazyColumn(
        modifier = Modifier
            .background(color = Color.LightGray)
            .padding(50.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(listOfProductCategories) { shoeCategories ->
            shoeCategories.products.forEach {
                if (it.bestChoiceOrNot) {
                    AdidasSection(
                        productName = it.name,
                        productPrice = it.startingPrice,
                        productImage = it.imageId,
                    )
                }
            }
        }
    }
}

@Composable
fun AdidasSection(productName: String, productPrice: Double, productImage: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "New Arrivals", fontWeight = FontWeight.Bold)

            Text(text = "See All", color = Color.Blue,fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(5.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .size(110.dp)
                .background(color = Color.LightGray)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text =  productName +'\n' + "RM%.2f".format(productPrice),
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = productImage),
                    contentDescription = null,

                    Modifier
                        .size(width = 150.dp, height = 100.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun Preview(){
   HomeScreen()
}