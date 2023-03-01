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

    data class DeleteActiveGoal(val goalItem: GoalItem):UIEvent()
    data class DeactivateSnackBar(val bool: Boolean): UIEvent()
    data class WriteMessageToSnackbar(val msg: String): UIEvent()

    data class EditableGoals(val enabled: Boolean):UIEvent()
    data class HistoricalActivityRecording(val enabled: Boolean): UIEvent()

    // Daily Status screen

    // History screen
}
