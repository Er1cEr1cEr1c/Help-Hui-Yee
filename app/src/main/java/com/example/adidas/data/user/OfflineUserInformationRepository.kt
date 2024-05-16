package com.example.adidas.data.user

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class OfflineUserInformationRepository (private val userInformationDao: UserInformationDao): UserInformationRepository{

    override fun getUserInformationStream(id: Int): Flow<UserInformation?> = userInformationDao.getUserInformation(id)
   @WorkerThread
   override suspend fun insertUser(userInformation: UserInformation){
       userInformationDao.insertUser(userInformation)
   }
    @WorkerThread
    override suspend fun updateUser(userInformation: UserInformation){
        userInformationDao.updateUser(userInformation)
    }
    @WorkerThread
    override suspend fun deleteUser(userInformation: UserInformation){
        userInformationDao.deleteUser(userInformation)
    }

}