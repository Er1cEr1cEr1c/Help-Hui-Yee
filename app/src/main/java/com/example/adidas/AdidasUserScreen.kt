package com.example.adidas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adidas.ui.feat_search_product.SearchScreen
import com.example.adidas.ui.feat_bidding.BidScreen
import com.example.adidas.ui.feat_home.HomeScreen

enum class UserScreenRoutes {
    Home,
    Search,
    Bidding,
    Cart,
    Profile,
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdidasUserScreen(
    navController: NavHostController = rememberNavController()
) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if(item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else {
                                        Badge()
                                    }
                                }
                            ) {
                                if (index == selectedItemIndex) {
                                    Icon(
                                        modifier = Modifier.size(20.dp),
                                        painter = painterResource(id = item.iconId),
                                        contentDescription = "",
                                        tint = Color.Green
                                    )
                                }
                                else {
                                    Icon(
                                        modifier = Modifier.size(20.dp),
                                        painter = painterResource(id = item.iconId),
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = UserScreenRoutes.Home.name
        ) {
            composable(route = UserScreenRoutes.Home.name) {
                HomeScreen()
            }

            composable(route = UserScreenRoutes.Search.name) {
                SearchScreen()
            }

            composable(route = UserScreenRoutes.Bidding.name) {
                BidScreen(
                    shoeModel = "",
                    shoeDescription = "",
                    shoeSize = "",
                    bidEndTimeStamp = "",
                    currentHighestBid = "",
                    yourBid = "",
                    bidValue = ""
                )
            }

            composable(route = UserScreenRoutes.Cart.name) {

            }

            composable(route = UserScreenRoutes.Profile.name) {

            }
        }
    }
}

val items = listOf(
    BottomNavigationItem(
        title = UserScreenRoutes.Home.name,
        iconId = R.drawable.home,
        selectedOrNot = true,
    ),
    BottomNavigationItem(
        title = "Search",
        iconId = R.drawable.search,
        selectedOrNot = false,
    ),
    BottomNavigationItem(
        title = UserScreenRoutes.Bidding.name,
        iconId = R.drawable.adidas_logo,
        selectedOrNot = false,
    ),
    BottomNavigationItem(
        title = UserScreenRoutes.Cart.name,
        iconId = R.drawable.shopping_cart,
        selectedOrNot = false,
    ),
    BottomNavigationItem(
        title =UserScreenRoutes.Profile.name,
        iconId = R.drawable.profile_user,
        selectedOrNot = false,
    ),
)

data class BottomNavigationItem(
    val title: String,
    val iconId: Int,
    val selectedOrNot: Boolean,
    val badgeCount: Int? = null
)