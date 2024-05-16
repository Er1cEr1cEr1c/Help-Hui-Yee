package com.example.adidas.data.bidding

import kotlinx.coroutines.flow.Flow

interface BiddingRepository {
    fun getAllBiddingStream(): Flow<List<Bidding>>

    fun getBiddingStream(id: Int): Flow<Bidding?>

    suspend fun insertBidding(bidding: Bidding)

    suspend fun deleteBidding(bidding: Bidding)

    suspend fun updateBidding(bidding: Bidding)
}