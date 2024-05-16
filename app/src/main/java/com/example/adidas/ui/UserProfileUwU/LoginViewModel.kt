package com.example.adidas.ui.UserProfileUwU

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.adidas.data.user.UserInformationRepository


class LoginViewModel (
    savedStateHandle: SavedStateHandle,
    private val userInformationRepository: UserInformationRepository
): ViewModel() {



}

data class UserInformationLoginUiState(
    val userSignUpDetails: UserSignUpDetails = UserSignUpDetails()
)