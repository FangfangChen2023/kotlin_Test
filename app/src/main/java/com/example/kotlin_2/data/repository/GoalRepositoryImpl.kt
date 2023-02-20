package com.example.kotlin_2.data.repository

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
    }

    override suspend fun getGoal(id: Int): GoalItem? {
        TODO("Not yet implemented")
    }

    override suspend  fun getAllGoals(): LiveData<List<GoalItem>> {
        return goalDao.readGoals()
    }

    override suspend fun getActiveGoal(): GoalItem? {
//        val allGoals = getAllGoals()
//        for (goal in allGoals){
//            if (goal.active){
//                return goal
//            }
//        }
//        return allGoals[0]
        return null
    }

    override suspend fun setActiveGoal(item: GoalItem) {
//        val allGoals = getAllGoals()
//        for (goal in allGoals){
//            goal.active = goal == item
//        }

    }

//    fun getAllGoals(): List<GoalItem>{
//        return listOf(
//            GoalItem(
//                steps = 1000,
//                name = "Easy",
//                active = true
//            ),
//            GoalItem(
//                steps = 5000,
//                name = "Medium",
//                active = false
//            ),
//            GoalItem(
//                steps = 10000,
//                name = "Hard",
//                active = false
//            ),
//
//            )
//    }
}