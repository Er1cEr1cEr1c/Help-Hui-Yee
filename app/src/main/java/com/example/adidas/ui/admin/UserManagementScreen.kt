package com.example.adidas.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
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

//@Composable
//fun UserManagementScreen(
//    onMenuCLicked:() -> Unit,
//    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
//){
//    val uiState by userViewModel.uiState.collectAsState()
//
//    UserManagementContent(
//        userList = uiState.userList,
//        onMenuCLicked = onMenuCLicked
//    )
//}


@Composable
fun UserManagementScreen(
    //userList: List<User>
    onMenuClicked:() -> Unit = {}
){
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ){
        Scaffold(
            topBar = {
                AdminAppBar(
                    currentScreen = AdminScreen.UserManagement,
                    onMenuClicked = onMenuClicked
                )
            }
        ) { innerPadding ->

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
//                LazyColumn {
//                    items(userList){item ->
//                        DisplayBody(
//                            id = item.,
//                            name = item.,
//                            point = item.
//                        )
//                    }
//                }
                DisplayBody()
            }
        }
    }
}

@Composable
fun DisplayBody(
//    id: String,
//    name: String,
//    point: Int
){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize()
    ){

        Column (Modifier.padding(10.dp)
        ){
            HeaderDisplay()
            DisplayUserDetails(userId = "001", userName = "Tan Ja Man",membershipPoint = 100)
            DisplayUserDetails(userId = "002", userName = "Ching E-Fye",membershipPoint = 200)
            DisplayUserDetails(userId = "003", userName = "Lim Shi Tao",membershipPoint = 300)
            DisplayUserDetails(userId = "004", userName = "Thong Hui Yee",membershipPoint = 100)
        }
    }
}

@Composable
fun HeaderDisplay(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        val (userID, userName, membershipPoint) = createRefs()
        val startGuideline = createGuidelineFromStart(0.dp)
        val midGuideLine = createGuidelineFromStart(70.dp)
        val endGuideline = createGuidelineFromEnd(4.dp)

        Text(
            text = "User ID",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(userID) {
                    start.linkTo(startGuideline)
                }
        )

        Text(
            text = "Name",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(userName) {
                    start.linkTo(midGuideLine)
                }
        )

        Text(
            text = "Membership Point",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(membershipPoint) {
                    end.linkTo(endGuideline)
                }
        )
    }

    Divider(
        thickness = 2.dp,
        color = Color.Black,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    )
}

@Composable
fun DisplayUserDetails(
    userId: String,
    userName: String,
    membershipPoint: Int
){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        val (id, name, point) = createRefs()
        val startGuideline = createGuidelineFromStart(0.dp)
        val midGuideLine = createGuidelineFromStart(70.dp)
        val endGuideline = createGuidelineFromEnd(4.dp)

        Text(
            text = userId,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(id) {
                    start.linkTo(startGuideline)
                }
        )

        Text(
            text = userName,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(name) {
                    start.linkTo(midGuideLine)
                }
        )

        Text(
            text = "" + membershipPoint,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(point) {
                    end.linkTo(endGuideline)
                }
        )
    }

    Divider(
        thickness = 1.dp,
        color = Color.Black,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun UserManagementScreenPreview() {
    AdidasTheme {
        UserManagementScreen()
    }
}