package com.example.kotlin_2.data.repository

import com.example.kotlin_2.data.model.HistoryItem
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun insertHistory(historyItem: HistoryItem)

    suspend fun deleteHistory(id: Int)

    suspend fun getHistory(id: Int) : HistoryItem?

    fun getHistory() : Flow<List<HistoryItem>>
}