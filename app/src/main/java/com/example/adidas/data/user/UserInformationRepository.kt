package com.example.adidas.data.user

import kotlinx.coroutines.flow.Flow

interface UserInformationRepository {

    fun getUserInformationStream(id: Int): Flow<UserInformation?>

    suspend fun insertUser(UserInformation: UserInformation)

    suspend fun deleteUser(UserInformation: UserInformation)

    suspend fun updateUser(UserInformation: UserInformation)
}