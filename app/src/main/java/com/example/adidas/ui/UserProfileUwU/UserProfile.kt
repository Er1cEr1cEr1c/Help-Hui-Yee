package com.example.adidas.ui.UserProfileUwU

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//pass value user
/*object UserProfileDestination : UserNavigation{
    const val UserIdArg = "userId"
}*/

@Composable
fun UserProfileScreen(
    OnSignOutButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        TextButton(onClick = OnSignOutButtonClicked) {
                Text(text = "Signout")
        }
    }
}