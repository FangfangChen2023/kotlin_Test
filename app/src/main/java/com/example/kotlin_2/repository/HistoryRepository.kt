package com.example.kotlin_2.repository

import com.example.kotlin_2.model.HistoryItem

class HistoryRepository {
    fun getAllHistory(): List<HistoryItem>{
        return listOf(
            HistoryItem(
                steps = 2000,
                hisDate = "12/12/2022"
            ),
            HistoryItem(
                steps = 2001,
                hisDate = "12/12/2022"
            ),
            HistoryItem(
                steps = 2002,
                hisDate = "12/12/2022"
            ),
            HistoryItem(
                steps = 2003,
                hisDate = "12/12/2022"
            ),
            HistoryItem(
                steps = 2004,
                hisDate = "12/12/2022"
            ),
            HistoryItem(
                steps = 2005,
                hisDate = "12/12/2022"
            ),
            HistoryItem(
                steps = 2006,
                hisDate = "12/12/2022"
            ),
            HistoryItem(
                steps = 2007,
                hisDate = "12/12/2022"
            ),
        )
    }
}