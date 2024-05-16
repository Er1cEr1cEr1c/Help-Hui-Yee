@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.assignmentstartqaq
//make a tap to start UwU


import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adidas.ui.AppViewModelProvider
import com.example.adidas.ui.UserProfileUwU.RegisterViewModel
import com.example.adidas.ui.UserProfileUwU.UserSignUpDetails
import com.example.adidas.ui.UserProfileUwU.UserSignUpUiState
import com.example.adidas.ui.UserProfileUwU.rememberImeState
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    OnBackToLoginButtonClicked: () -> Unit,
    OnSignUpButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()


        Column (
            modifier = Modifier
                .fillMaxSize(),
//                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
                UserRegisterBody(
                    OnAlreadyHaveAccButtonClicked = {OnBackToLoginButtonClicked()},
                    userSignUpDetails = registerViewModel.userSignUpUiState.userSignUpDetails,
                    userSignUpUiState = registerViewModel.userSignUpUiState,
                    onSignUpInformationChanged = registerViewModel::updateUiState,
                    onSignUpClicked = {
                        if (registerViewModel.userSignUpUiState.validationForPassword)
                        coroutineScope.launch {
                            registerViewModel.saveUserSignUpIntoDB()
                            OnSignUpButtonClicked()
                        }
                    }
                )

        }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterBody(
    OnAlreadyHaveAccButtonClicked: () -> Unit,
    userSignUpDetails: UserSignUpDetails,
    userSignUpUiState: UserSignUpUiState,
    onSignUpInformationChanged: (UserSignUpDetails) -> Unit,
    onSignUpClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    val imeVisibility by rememberImeState()
    //remember to add android.windowSoftInputMode "adjustResize" but now use "adjustNothing" at manifest file
    var passwordVisibility by remember{ mutableStateOf(false) }

    BackgroundColor {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()

        ) {
            val animationForKeyboardShowsUp by animateFloatAsState(targetValue = if (imeVisibility) 0f else 0.3f)
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

                Text(
                    text = "Sign Up Account",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
                    color  = Color.DarkGray,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 13.dp)
                )

                Spacer(modifier = Modifier.fillMaxSize(0.05f))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 13.dp, bottom = 8.dp),
                    value = userSignUpDetails.userEmail,
                    onValueChange = { onSignUpInformationChanged(userSignUpDetails.copy(userEmail = it))},
                    label = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                )
                Spacer(modifier = Modifier.fillMaxSize(0.05f))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 8.dp),
                    value = userSignUpDetails.userPhoneNumber,
                    onValueChange = { onSignUpInformationChanged(userSignUpDetails.copy(userPhoneNumber = it))},
                    label = { Text(text ="Phone Number") }
                )

                Spacer(modifier = Modifier.fillMaxSize(0.05f))
                //Password TextField
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 8.dp),
                    value = userSignUpDetails.userPassword,
                    onValueChange = { onSignUpInformationChanged(userSignUpDetails.copy(userPassword = it))},
                    label = { Text(text = "Password") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black
                    )


                )

                Spacer(modifier = Modifier.fillMaxSize(0.05f))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 25.dp),
                    value = userSignUpDetails.userPasswordConfirmation,
                    onValueChange = { onSignUpInformationChanged(userSignUpDetails.copy(userPasswordConfirmation = it))},
                    label = { Text(text = "Password confirm") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    visualTransformation =
                    if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()

                )


                Box(modifier = Modifier
                    .fillMaxHeight(0.5f),
                    contentAlignment = Alignment
                        .BottomCenter,

                    ){
                    Button(
                        onClick = onSignUpClicked,
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF080809)
                        ),
                        enabled = userSignUpUiState.validateForEmptyEntry

                    ) {
                        Text(
                            text = "Sign up",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(600))

                        )
                    }

                }
                TextButton(onClick = OnAlreadyHaveAccButtonClicked) {

                    Text(text = "Already have an account?")

                }

            }

        }

    }
}