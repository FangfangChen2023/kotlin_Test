package com.example.kotlin_2.data.repository

import com.example.kotlin_2.data.dao.DailyDao
import com.example.kotlin_2.data.model.DailyStatus

class DailyRepositoryImpl(
    private val dao: DailyDao
) : DailyRepository {
    override suspend fun insertDaily(dailyStatus: DailyStatus) {
        dao.insertDaily(dailyStatus)
    }

    override suspend fun deleteDaily(dailyStatus: DailyStatus) {
        dao.deleteDaily(dailyStatus)
    }

    override suspend fun getDaily(): DailyStatus? {
        return dao.getDaily()
    }

    override suspend fun updateDaily(dailyStatus: DailyStatus) {
        return dao.updateDaily(dailyStatus)
    }

}