package com.example.adidas.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    isPermanentlyDeclined: Boolean,
    onDismiss:() -> Unit,
    onOkClick:() -> Unit,
    onGoToAppSettingClick:() -> Unit,
){
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Divider()
                Text(
                    text =
                    if(isPermanentlyDeclined){
                        "Grant Permission"
                    }
                    else{
                        "OK"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToAppSettingClick()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(16.dp)
                )
            }
        },
        title = {
            Text(text = "Permission Required!")
        },
        text = {
            Text(
                text =
                if (isPermanentlyDeclined) {
                    "Please grant the access in the app settings!"
                } else {
                    "This allows you to get the images in the Gallery."
                }
            )
        }
    )
}