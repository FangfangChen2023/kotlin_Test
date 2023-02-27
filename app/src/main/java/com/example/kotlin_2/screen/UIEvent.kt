package com.example.kotlin_2.screen

import com.example.kotlin_2.data.model.DailyStatus
import com.example.kotlin_2.data.model.GoalItem

sealed class UIEvent {
    // Goals screen
    data class SetGoalActive(val goalItem : GoalItem):UIEvent()
    data class ClickOnGoal(val goalItem: GoalItem, val dailyStatus: DailyStatus):UIEvent()
    data class AddGoal(val name: String, val steps: Int):UIEvent()
    data class DeleteGoal(val goalItem: GoalItem):UIEvent()
    data class EditGoal(val goalItem: GoalItem):UIEvent()

    // Daily Status screen

    // History screen
}
