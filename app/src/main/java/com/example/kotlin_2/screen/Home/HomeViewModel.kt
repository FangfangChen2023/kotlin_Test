package com.example.kotlin_2.screen.Home

import DataBaseHandler
import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_2.data.model.HistoryItem
import java.time.LocalDateTime

class HomeViewModel(application: Application) :AndroidViewModel(application) {

    var currentStepsPref = application.getSharedPreferences("currentSteps", Application.MODE_PRIVATE)
    var datePref = application.getSharedPreferences("date",Application.MODE_PRIVATE)
    var goalPref = application.getSharedPreferences("goal",Application.MODE_PRIVATE)

    val editor = currentStepsPref.edit()
    val editorDay = datePref.edit()
    val editorGoal = goalPref.edit()

    private val _currentSteps= MutableLiveData(currentStepsPref.getInt("currentSteps", 0))
    val currentSteps:LiveData<Int> = _currentSteps
    var steps = currentStepsPref.getInt("currentSteps", 0)

    fun keyboardAction(
        application: Application = Application(),
        stepsInput: Int = 0
    ){
        _currentSteps.value = steps + stepsInput
        editor.putInt("currentSteps", steps + stepsInput)
        editor.commit()

        var currentDate = LocalDateTime.now()
        val oldDate = datePref.getString("date", null)
        var dateMemorised: LocalDateTime? = null
        if (oldDate != null) {
            dateMemorised = LocalDateTime.parse(oldDate)
        }
        //var dateMemorised = LocalDateTime.parse(sharedPreferenceDay.getString("date", null))
        //TODO change this to days
        val start = currentDate.minute
        var end = 0
        if (dateMemorised == null) {
            end = currentDate.minute
        } else {
            end = dateMemorised.minute
        }
        //Toast.makeText(context, "$start, $end, $oldDate", Toast.LENGTH_SHORT).show()
        if (start != end && (dateMemorised != null)) {
            // next day! commit to history data base, set to default values
//            val db = DataBaseHandler(application)
            //var activeGoal = db.getActiveGoal()
            var goal = goalPref.getString("goal", null)
            if (goal == null) {
                goal = "default"
            }
            var steps = currentStepsPref.getInt("currentSteps", 0)
            //Toast.makeText(context, "something happened at least,$dateMemorised, $goal, $steps", Toast.LENGTH_SHORT).show()
            val history = HistoryItem(
                dateMemorised,
                goal,
                currentStepsPref.getInt("currentSteps", 0)
            )
//            db.insertDayStatus(history)
            //var histories = db.readHistory()
            editor.putInt("currentSteps", 0)
            editorGoal.putString("name", goal)
            editor.commit()
            editorGoal.commit()
            //Toast.makeText(context, "something happened at least,$history, $histories", Toast.LENGTH_SHORT).show()
        }
        editorDay.putString("date", currentDate.toString());
        editorDay.commit()

        //var date = datePref.getString("date", null)
        //var curdate = currentDate.toString()
        //Toast.makeText(context, "commited current date, $curdate, $date", Toast.LENGTH_SHORT).show()
    }



}