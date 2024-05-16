package com.example.adidas.data.bidding

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BiddingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBidding(bidding: Bidding)

    @Update
    suspend fun updateBidding(bidding: Bidding)

    @Delete
    suspend fun deleteBidding(bidding: Bidding)

    @Query("SELECT * from bidding WHERE biddingId = :id")
    fun getBidding(id: Int): Flow<Bidding>

    @Query("SELECT * from bidding ORDER BY biddingId ASC")
    fun getAllBidding(): Flow<List<Bidding>>
}