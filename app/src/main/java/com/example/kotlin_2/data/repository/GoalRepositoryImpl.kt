package com.example.kotlin_2.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlin_2.data.dao.GoalDao
import com.example.kotlin_2.data.model.GoalItem
import kotlinx.coroutines.flow.Flow

class GoalRepositoryImpl (
    private val goalDao: GoalDao
        ) : GoalRepository {
    override suspend fun insertGoal(goalItem: GoalItem) {
        goalDao.insertGoal(goalItem)
    }

    override suspend fun deleteGoal(goalItem: GoalItem) {
        goalDao.deleteGoal(goalItem)
        var goals = getAllGoals().value
        if(goals!!.count()==1){
            goals.first().active = true
            updateGoal(goals.first())
        }
    }

    override suspend fun updateGoal(goalItem: GoalItem){
        goalDao.updateGoal(goalItem)
    }

    override suspend fun getGoal(id: Int): GoalItem? {
        TODO("Not yet implemented")
    }

    override suspend  fun getAllGoals(): LiveData<List<GoalItem>> {
        return goalDao.readGoals()
    }

    override suspend fun getActiveGoal(): GoalItem {
        return goalDao.getActiveGoal()
    }

    override suspend fun setActiveGoal(goalItem: GoalItem) {
        goalItem.active = true
        val activeGoal = goalDao.getActiveGoal()
        updateGoal(goalItem)
        if (activeGoal != null && activeGoal != goalItem) {
            activeGoal.active = false
            updateGoal(activeGoal)
        }

    }

}