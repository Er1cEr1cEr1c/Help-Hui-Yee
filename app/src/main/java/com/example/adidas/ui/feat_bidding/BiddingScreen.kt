package com.example.adidas.ui.feat_bidding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun BidScreen(
    shoeModel: String,
    shoeDescription: String,
    shoeSize: String,
    bidEndTimeStamp: String,
    currentHighestBid: String,
    yourBid: String,
    bidValue: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Row(
            modifier = Modifier
                .background(Color.Cyan)
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
                .aspectRatio(1f)
                .weight(1f),
            painter = painterResource(id = R.drawable.shoe_rm),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .weight(1f),
        ) {
            Text(
                text = shoeModel, fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = shoeDescription,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Size: " + shoeSize,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = bidEndTimeStamp,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Current highest bid: ",
                    fontSize = 20.sp
                )
                Text(
                    text = currentHighestBid,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your bid: ",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Row {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(100),
                        modifier = Modifier
                            .size(30.dp)
                    ) {
                        Text(text = "-", fontSize = 14.sp) // Adjusted text size
                    }
                    Text(
                        text = yourBid,
                        fontSize = 20.sp
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(100),
                        modifier = Modifier
                            .size(30.dp)
                    ) {
                        Text(text = "+", fontSize = 14.sp, color = Color.Black) // Adjusted text size
                    }
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = bidValue,
                    fontSize = 15.sp
                )
            }


            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

                ) {
                    Text(text = "Place My Bid")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBidScreen() {
    BidScreen(
        shoeModel = "Adidas Originals Men",
        shoeDescription = "Adidas Original Men, your smart, Gorgeous & Fashionable Collection",
        shoeSize = "35",
        bidEndTimeStamp = "Ends in 15h:28m:00s",
        currentHighestBid = "RM1000.00",
        yourBid = " RM200.00 ",
        bidValue = "Each increase is RM50"
    )
}