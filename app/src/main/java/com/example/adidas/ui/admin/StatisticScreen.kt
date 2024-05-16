package com.example.adidas.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adidas.AdminAppBar
import com.example.adidas.AdminScreen
import com.example.adidas.ui.theme.AdidasTheme

@Composable
fun StatisticScreen(
    onMenuClicked:() -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Scaffold(
            topBar = {
                AdminAppBar(
                    currentScreen = AdminScreen.Statistic,
                    onMenuClicked = onMenuClicked
                )
            }
        ) { innerPadding ->
            Modifier.padding(4.dp)

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Column(Modifier.padding(20.dp)
                ){
                    StatisticReport()
                    Spacer(modifier = Modifier.padding(20.dp))
                    StatisticReport()
                }
            }
        }
    }
}

@Composable
fun StatisticReport(){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .size(width = 340.dp, height = 100.dp),
    ){
        Column (Modifier.padding(6.dp)
        ){
            Text(text = "Total Sales in a month (RM)")


        }
    }
}

@Preview
@Composable
fun StatisticPreview() {
    AdidasTheme {
        StatisticScreen()
    }
}