package com.example.adidas.data.bidding

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class OfflineBiddingRepository(private val biddingDao: BiddingDao) : BiddingRepository{
    override fun getAllBiddingStream(): Flow<List<Bidding>> = biddingDao.getAllBidding()

    override fun getBiddingStream(id: Int): Flow<Bidding?> = biddingDao.getBidding(id)

    @WorkerThread // TO run in background (can be stopped and run later)
    override suspend fun insertBidding(bidding: Bidding) = biddingDao.insertBidding(bidding)

    @WorkerThread
    override suspend fun deleteBidding(bidding: Bidding) = biddingDao.deleteBidding(bidding)

    @WorkerThread
    override suspend fun updateBidding(bidding: Bidding) = biddingDao.insertBidding(bidding)
}