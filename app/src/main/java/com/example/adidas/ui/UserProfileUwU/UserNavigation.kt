package com.example.adidas.ui.UserProfileUwU

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignmentstartqaq.LoginScreen
import com.example.assignmentstartqaq.RegisterScreen


enum class UserNavigation(val title:String) {
    UserProfile(title = "User Profile"),
    Login(title = "User Login"),
    SignUp(title = "User SignUp"),
    ForgotPassword(title = "User Forgot Password"),
}

@Composable
fun NavigationForUserProfileFunction(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = UserNavigation.Login.name) {

        //Navigate to Login screen
        composable(route = UserNavigation.Login.name){
            LoginScreen(

                OnNoAccountButtonClicked = {
                    navController
                        .navigate(UserNavigation.SignUp.name)
                                        },

                OnForgotPasswordClicked = {
                    navController
                        .navigate(UserNavigation.ForgotPassword.name)
                                          },

                OnLoginCliked = {
                    navController
                        .navigate("${UserNavigation.UserProfile.name}")
                }
            )

        }


        //Navigate to Sign Up screen
        composable(route = UserNavigation.SignUp.name){
            RegisterScreen(
                OnBackToLoginButtonClicked = {
                    navController
                        .navigateUp()
                },

                OnSignUpButtonClicked = {
                    navController
                        .navigateUp()
                }
            )

        }

        //Navigate to ForgotPassword screen
        composable(route = UserNavigation.ForgotPassword.name){
            ForgotPasswordScreen(OnForgotPasswordClicked = {
                navController
                    .navigateUp()
            }
            )
        }

        //Navigate to User Profile screen
        composable(route = UserNavigation.UserProfile.name){
            UserProfileScreen(

                /*OnMemberShipRewardClicked = {
                    navController
                },
                OnPaymentDetailClicked = {
                    navController
                },
                OnPurchaseHistoryClicked = {
                    navController
                },
                OnDeleteAccountClicked = {
                    navController
                },
                OnModifyAccountDetailClicked = {
                    navController.navigate()
                },*/
                OnSignOutButtonClicked = {
                    navController
                        .navigateUp()
                }

            )
        }

        composable(route = UserNavigation.ForgotPassword.name){
            ForgotPasswordScreen(OnForgotPasswordClicked = {
                navController
                    .navigateUp()
            }
            )
        }

    }
}