package com.example.assignmentstartqaq

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.adidas.ui.UserProfileUwU.NavigationForUserProfileFunction
import com.example.adidas.ui.UserProfileUwU.rememberImeState


@Composable
fun UserApp(navController: NavHostController = rememberNavController()){
    NavigationForUserProfileFunction(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    OnNoAccountButtonClicked:() -> Unit,
    OnForgotPasswordClicked:() -> Unit,
    OnLoginCliked:() -> Unit,
    modifier: Modifier = Modifier) {


    val imeVisibility by rememberImeState()
    //remember to add android.windowSoftInputMode "adjustResize" but now use "adjustNothing" at manifest file
    var logInPassword by remember{ mutableStateOf("") }
    var logInEmail by remember{ mutableStateOf("") }
    var passwordVisibility by remember{ mutableStateOf(false) }

    BackgroundColor {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val animationForKeyboardShowsUp by animateFloatAsState(targetValue = if (imeVisibility) 0f else 0.35f)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(animationForKeyboardShowsUp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier

                ) {
                    Text(
                        text = "Adidias Apps",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                    Text(
                        text = "Welcome and enjoy!",
                        color = Color.Gray
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color.White)
            ) {

                Spacer(modifier = Modifier.fillMaxSize(0.05f))
                Text(
                    text = "Login Account",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
                    color  = Color.DarkGray
                )

                Spacer(modifier = Modifier.fillMaxSize(0.05f))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    value = logInEmail,
                    onValueChange = { logInEmail = it },
                    label = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

                )
                Spacer(modifier = Modifier.height(16.dp))
                //Password TextField
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    value = logInPassword,
                    onValueChange = { logInPassword = it },
                    label = { Text(text = "Password") },
                    singleLine = true,
                    trailingIcon = {
                        TextButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {  //!passwordVisible means oposite value of the boolean
                            Text(
                                text =
                                if (passwordVisibility) "Hide"
                                else "show"
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    visualTransformation =
                    if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()

                )
                
                TextButton(
                    onClick = OnForgotPasswordClicked,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp)
                        .fillMaxHeight(0.1f)

                ) {
                    Text(text = "forgot password")
                }


                Box(modifier = Modifier
                    .fillMaxHeight(0.5f),
                    contentAlignment = Alignment
                        .BottomCenter,

                    ){
                    Button(
                        onClick = OnLoginCliked,
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF080809)
                        )
                    ) {
                        Text(
                            text = "Login Account",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(600))

                        )
                    }

                }
                TextButton(onClick = OnNoAccountButtonClicked) {
                    Text(text = "Sign up account for free >_<")
                }

            }

        }

    }

}

@Composable
fun BackgroundColor(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val deepColor = Color(0xFF080809)
    val lightColor = Color(0xFF6DF7F2)

    Box(
        modifier = modifier.background(brush = Brush.linearGradient(
            listOf(
                deepColor, lightColor
            )
        ))
    ){
        content()
    }
}