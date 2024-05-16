package com.example.adidas.ui.UserProfileUwU

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.adidas.data.user.UserInformation
import com.example.adidas.data.user.UserInformationRepository

class RegisterViewModel (private val userInformationRepository: UserInformationRepository): ViewModel() {

    var userSignUpUiState by mutableStateOf(UserSignUpUiState())
        private set

    fun updateUiState(userSignUpDetails: UserSignUpDetails){
        userSignUpUiState = UserSignUpUiState(
            userSignUpDetails = userSignUpDetails ,
            validateForEmptyEntry = validationEmptyEntry(userSignUpDetails),
            validationForPassword = validationForPassword(userSignUpDetails),
            validationForEmailFormat = validationForEmailFormat(userSignUpDetails),
            validationForPasswordStrength = validationForPasswordStrength(userSignUpDetails)
        )
    }

    suspend fun saveUserSignUpIntoDB(){
        if (validationEmptyEntry()) {
            userInformationRepository.insertUser(userSignUpUiState.userSignUpDetails.toUserSignUp())
        }
        }

    private fun validationEmptyEntry(uiState: UserSignUpDetails = userSignUpUiState.userSignUpDetails): Boolean {
        return with(uiState){
            userEmail.isNotBlank() && userPassword.isNotBlank() && userPhoneNumber.isNotBlank()
        }
    }

    private fun validationForPassword(uiState: UserSignUpDetails = userSignUpUiState.userSignUpDetails): Boolean{
        return with(uiState){
            userPassword == userPasswordConfirmation
        }
    }

    private fun validationForEmailFormat(uiState: UserSignUpDetails = userSignUpUiState.userSignUpDetails): Boolean{
        val emailFormatEnsure = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.com)"
        return with(uiState){
            emailFormatEnsure.toRegex().matches(userEmail)
        }
    }

    private fun validationForPasswordStrength(uiState: UserSignUpDetails = userSignUpUiState.userSignUpDetails): Boolean{

        return with(uiState){
            (userPassword.length < 1 || userPassword.length > 20 )
        }
    }

}

data class UserSignUpUiState(
    val userSignUpDetails: UserSignUpDetails = UserSignUpDetails(),
    val validateForEmptyEntry: Boolean = false,
    val validationForPassword: Boolean = false,
    val validationForEmailFormat:  Boolean = false,
    val validationForPasswordStrength: Boolean = false
)

data class UserSignUpDetails(
    val userId: Int = 0,
    val userEmail:  String = "",
    val userPassword: String = "",
    val userPhoneNumber: String = "",
    val userPasswordConfirmation: String = ""
)

fun UserSignUpDetails.toUserSignUp(): UserInformation = UserInformation(
    userId = userId,
    userEmail = userEmail,
    userPassword = userPassword,
    userPhoneNumber = userPhoneNumber,
    userPasswordConfirmation = userPasswordConfirmation
)

fun UserInformation.toUserSignUpUiState(validateForEmptyEntry: Boolean = false): UserSignUpUiState = UserSignUpUiState(
    userSignUpDetails = this.toUserSignUpDetails(),
    validateForEmptyEntry =  validateForEmptyEntry
)

fun UserInformation.toUserSignUpDetails(): UserSignUpDetails = UserSignUpDetails(
    userId = userId,
    userEmail = userEmail,
    userPassword = userPassword,
    userPhoneNumber = userPhoneNumber,
    userPasswordConfirmation = userPasswordConfirmation
)