package com.example.kotlin_2.data.repository

import com.example.kotlin_2.data.model.DailyStatus
import kotlinx.coroutines.flow.Flow

interface DailyRepository {
    suspend fun insertDaily(dailyStatus: DailyStatus)

    suspend fun deleteDaily(dailyStatus: DailyStatus)

    //This table can only have one data
    suspend fun getDaily() : DailyStatus?

    suspend fun updateDaily(dailyStatus: DailyStatus)

}