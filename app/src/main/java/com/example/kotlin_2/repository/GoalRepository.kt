package com.example.kotlin_2.repository

import com.example.kotlin_2.model.GoalItem
import com.example.kotlin_2.model.HistoryItem

class GoalRepository {


    fun getAllGoals(): List<GoalItem>{
        return listOf(
            GoalItem(
                steps = 1000,
                name = "Easy",
                active = true
            ),
            GoalItem(
                steps = 5000,
                name = "Medium",
                active = false
            ),
            GoalItem(
                steps = 10000,
                name = "Hard",
                active = false
            ),

        )
    }
    fun getActiveGoal(): GoalItem? {
        val allGoals =  getAllGoals()
        for (goal in allGoals){
            if (goal.active){
                return goal
            }
        }
        return allGoals[0]
    }

    fun setActiveGoal(item: GoalItem) {
        val allGoals = getAllGoals()
        for (goal in allGoals){
            goal.active = goal == item
        }

    }
}