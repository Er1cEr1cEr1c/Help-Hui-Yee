package com.example.adidas.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usersInformation")
data class UserInformation(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val userEmail: String,
    val userPassword: String,
    val userPhoneNumber: String,
    val userPasswordConfirmation: String

)
