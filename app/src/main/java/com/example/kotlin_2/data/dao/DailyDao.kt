package com.example.kotlin_2.data.dao

import androidx.room.*
import com.example.kotlin_2.data.model.DailyStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaily(dailyStatus: DailyStatus)

    @Delete
    suspend fun deleteDaily(dailyStatus: DailyStatus)

    @Query("select * from DailyStatus")
    suspend fun getDaily() : DailyStatus?

    @Update
    suspend fun updateDaily(dailyStatus: DailyStatus)

}