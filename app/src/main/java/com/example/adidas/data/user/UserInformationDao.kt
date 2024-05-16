package com.example.adidas.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInformationDao {


    @Query("SELECT * from usersInformation WHERE userId = :id")
    fun getUserInformation(id: Int): Flow<UserInformation>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userInformation: UserInformation)

    @Update
    suspend fun updateUser(userInformation: UserInformation)

    @Delete
    suspend fun deleteUser(userInformation: UserInformation)
}