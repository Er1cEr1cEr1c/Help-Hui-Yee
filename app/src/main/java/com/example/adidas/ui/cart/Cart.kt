package com.example.adidas.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.adidas.R
import com.example.adidas.data.cart.CartEntity
import com.example.adidas.data.cart.cartList
import com.example.adidas.ui.AppViewModelProvider
import com.example.adidas.ui.UserProfileUwU.RegisterViewModel
import com.example.adidas.ui.UserProfileUwU.UserSignUpUiState
import com.example.adidas.ui.admin.viewModel.ProductDetailsViewModel
import com.example.adidas.ui.cart.uhh.DialogWithImage

@Composable
fun CartApp(navController: NavHostController = rememberNavController()) {
    CartNavigation(navController = navController)
}

@Composable
fun ShoppingCartScreen(
    onCheckoutButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
    productDetailsViewModel: ProductDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    registerViewModel: RegisterViewModel = viewModel(factory = AppViewModelProvider.Factory),
    orderViewModel: OrderDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val testProduct by productDetailsViewModel.uiState.collectAsState()
    val testUser: UserSignUpUiState = registerViewModel.userSignUpUiState
    val testOrder by orderViewModel.uiState.collectAsState()
    val uiState by cartViewModel.uiState.collectAsState()

    ShoppingCartContent(
        cartList = uiState.cartList,
        memberPoints = uiState.memberPoints,
        subtotal = uiState.subtotal,
        shippingFee = uiState.shippingFee,
        redeemButton = remember(cartViewModel) { cartViewModel::redeemPointsButton },
        redeemPoints = uiState.redeemPoints,
        pointsRedeemed = uiState.pointsRedeemed,
        totalPrice = uiState.totalPrice,
        onIncreaseClicked = remember(cartViewModel) { cartViewModel::increaseOrderQuantity },
        onDecreaseClicked = remember(cartViewModel) { cartViewModel::decreaseOrderQuantity },
        onRemoveItemClick = remember(cartViewModel) { cartViewModel::removeProductFromCart },
        backToHome = {},
        onCheckoutButtonClicked = onCheckoutButtonClicked,
        onBackButtonClicked = onBackButtonClicked
    )

}

@Composable
fun ShoppingCartContent(
    cartList: List<CartEntity>,
    memberPoints: Double,
    subtotal: Double,
    shippingFee: Double,
    redeemButton: () -> Unit,
    redeemPoints: Boolean,
    pointsRedeemed: Double,
    totalPrice: Double,
    onIncreaseClicked: (Int) -> Unit,
    onDecreaseClicked: (Int) -> Unit,
    onRemoveItemClick: (Int) -> Unit,
    backToHome: () -> Unit,
    onCheckoutButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    Scaffold (
        topBar = {
                 CartAppBar(
                     currentScreen = CartScreen.Cart,
                     canNavigateBack = true,
                     navigateUp = onBackButtonClicked
                 )
        },

        bottomBar = {
            PriceCard(
                subtotal = subtotal,
                memberPoints = memberPoints,
                shippingFee = shippingFee,
                redeemButton = redeemButton,
                redeemPoints = redeemPoints,
                pointsRedeemed = pointsRedeemed,
                totalPrice = totalPrice,
                backToHome = backToHome,
                onCheckoutButtonClicked = onCheckoutButtonClicked,
                enableButton = true,
                enableDialog = false
            )
        }

    ) {
        innerPadding ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
//                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(cartList) {cartItem ->
                    ShoppingCartItem(
                        cartItem = cartItem,
                        enableQuantityButton = true,
                        onIncreaseClicked = remember {{ onIncreaseClicked(cartItem.cartEntityId) }},
                        onDecreaseClicked = remember {{ onDecreaseClicked(cartItem.cartEntityId) }},
                        enableRemoveButton = true,
                        onRemoveItemClick = { onRemoveItemClick(cartItem.cartEntityId) },
                    )

                }
            }
        }
    }
}

@Composable
fun ShoppingCartItem(
    cartItem: CartEntity,
    enableQuantityButton: Boolean,
    onIncreaseClicked: () -> Unit,
    onDecreaseClicked: () -> Unit,
    enableRemoveButton: Boolean,
    onRemoveItemClick: () -> Unit,
) {
    var count by rememberSaveable { mutableIntStateOf(cartItem.orderQty) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .size(width = 340.dp, height = 100.dp),
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(cartItem.imageResourceId),
                contentDescription = "Yeezy Boost 350 V2",
                modifier = Modifier
                    .fillMaxHeight()
                    .size(100.dp)
            )
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = cartItem.productName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )

                    Text(
                        text = "RM ${formattedPrice(cartItem.price * cartItem.orderQty)}",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(top = 3.dp)
                    )

                    if (enableQuantityButton) {
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        if (count != 1){
                                            count--
                                            onDecreaseClicked()
                                        }
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.remove_circle_outline_24),
                                    contentDescription = ""
                                )
                            }
                            Text(
                                text = "${cartItem.orderQty}",
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .clickable {
                                        if (count < 10){
                                            count++
                                            onIncreaseClicked()
                                        }
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.round_add_circle_24),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                    else {
                        Text(
                            text = "x ${cartItem.orderQty}",
                            modifier = Modifier
                                .padding(top = 5.dp)

                        )
                    }

                }
                Column (
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){


                    Text(
                        text = cartItem.size,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(top = 20.dp, end = 5.dp)
                    )

                    if (enableRemoveButton) {
                        IconButton(onClick = { onRemoveItemClick() }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete",
                            )
                        }
                    }
                }
            }
        }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun PriceCard (
    subtotal: Double,
    memberPoints: Double,
    shippingFee: Double,
    redeemButton: () -> Unit,
    redeemPoints: Boolean,
    pointsRedeemed: Double,
    totalPrice: Double,
    backToHome: () -> Unit,
    onCheckoutButtonClicked: () -> Unit,
    enableButton: Boolean,
    enableDialog: Boolean
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        PriceText(
            memberPoints = memberPoints,
            subtotal = subtotal,
            shippingFee = shippingFee,
            redeemButton = redeemButton,
            redeemPoints = redeemPoints,
            pointsRedeemed = pointsRedeemed
            )

        Divider(color = Color.LightGray, thickness = 2.dp)

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 9.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Total",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "RM ${formattedPrice(totalPrice)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )
        }

        if(enableButton) {
            Button(
                onClick = { onCheckoutButtonClicked() },
                modifier = Modifier
                    .size(width = 350.dp, height = 40.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color.Black),

                ) {
                Text("Checkout")
            }
        }
        if(enableDialog){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DialogWithImage(
                    titleText = "Your payment is Successful",
                    enableSmallText = true,
                    backToShopping = backToHome,
                    toOrderDetails = onCheckoutButtonClicked,
                )
            }
        }
            Spacer(modifier = Modifier.height(15.dp))
    }
}



@Composable
fun PriceText(
    memberPoints: Double,
    subtotal: Double,
    shippingFee: Double,
    redeemButton: () -> Unit,
    redeemPoints: Boolean,
    pointsRedeemed: Double,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 9.dp),
        horizontalArrangement = Arrangement.spacedBy(115.dp)
    ){
        Text(
            text = "Redeem ${formattedInt(memberPoints)} Membership Points",
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = Color(0xFF5B99E1),
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Box(
            modifier = Modifier
                .clickable { redeemButton() }
        ) {
            Image(
                painter = painterResource(
                    id = when (redeemPoints) {
                        true -> R.drawable.baseline_check_circle_24
                        false -> R.drawable.radio_button_unchecked_24
                    }
                ),
                contentDescription = "Redeem Membership Points Icon"
            )
        }
    }

    Row (
        modifier = Modifier
            .padding(vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){


        Text(
            text = "Subtotal",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "RM ${formattedPrice(subtotal)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Shipping Fee",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
        
        Text(
            text = "RM ${formattedPrice(shippingFee)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Points Redeemed",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "- RM ${formattedPrice(pointsRedeemed)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
    }

}

fun formattedPrice(price: Double): String {
    return String.format("%.2f", price)
}

fun formattedInt(number: Double): String {
    return String.format("%.0f", number)
}

@Preview(showBackground = true)
@Composable
fun AdidasAppPreview(modifier: Modifier = Modifier) {
    ShoppingCartContent(
        cartList =  cartList,
        memberPoints = 150.0,
        subtotal = 2996.00,
        shippingFee = 25.00,
        redeemButton = {},
        redeemPoints = true,
        pointsRedeemed = 150.0,
        totalPrice = 2871.0,
        onIncreaseClicked = {},
        onDecreaseClicked = {},
        onRemoveItemClick = {},
        onBackButtonClicked = {},
        backToHome = {},
        onCheckoutButtonClicked = {}
    )
}
