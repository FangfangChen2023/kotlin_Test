package com.example.kotlin_2.screen.Home


import android.util.Log
import androidx.lifecycle.*
import com.example.kotlin_2.data.model.DailyStatus
import com.example.kotlin_2.data.repository.DailyRepository
import com.example.kotlin_2.data.repository.DailyRepositoryImpl
import com.example.kotlin_2.data.repository.GoalRepository
import com.example.kotlin_2.data.repository.GoalRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dailyRepository: DailyRepository,
    private  val goalRepository: GoalRepository

) : ViewModel() {

    var dailyDB: DailyStatus? = null
    var isDailyNotNull = MutableLiveData(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dailyDB = dailyRepository.getDaily()
            if (dailyDB == null) {
                isDailyNotNull.postValue(true)
            }
//            this@HomeViewModel.dailyDate = dailyRepository.getDaily()!!.todayDate
        }
    }

//    var currentStepsPref = application.getSharedPreferences("currentSteps", Application.MODE_PRIVATE)
//    var datePref = application.getSharedPreferences("date",Application.MODE_PRIVATE)
//    var goalPref = application.getSharedPreferences("goal",Application.MODE_PRIVATE)

//    val editor = currentStepsPref.edit()
//    val editorDay = datePref.edit()
//    val editorGoal = goalPref.edit()

    private val _currentSteps = MutableLiveData( if(dailyDB!=null) dailyDB!!.currentSteps else 0)
    val currentSteps: LiveData<Int> = _currentSteps
    var steps = if(dailyDB!=null) dailyDB!!.currentSteps else 0


    fun keyboardAction(
        stepsInput: Int = 0
    ) {
        _currentSteps.postValue(steps + stepsInput)
        if(dailyDB!=null) {
            dailyDB!!.currentSteps = steps + stepsInput
            viewModelScope.launch(Dispatchers.IO) {
                //update the new steps into database daily status table
                dailyRepository.updateDaily(dailyDB!!)
            }
        }


        /*var currentDate = LocalDateTime.now()
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
        editorDay.commit()*/

        //var date = datePref.getString("date", null)
        //var curdate = currentDate.toString()
        //Toast.makeText(context, "commited current date, $curdate, $date", Toast.LENGTH_SHORT).show()
    }


}