package com.example.kotlin_2.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_2.data.dao.GoalDao
//import com.example.kotlin_2.data.dao.HistoryDao
import com.example.kotlin_2.data.model.GoalItem
import com.example.kotlin_2.data.model.HistoryItem

@Database(entities = [GoalItem::class, HistoryItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    //abstract fun historyDao(): HistoryDao



}

