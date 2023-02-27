package com.example.kotlin_2.screen.Goal

import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_2.data.model.DailyStatus
import com.example.kotlin_2.data.model.GoalItem
import com.example.kotlin_2.data.repository.DailyRepository
import com.example.kotlin_2.data.repository.GoalRepository
import com.example.kotlin_2.screen.Home.HomeViewModel
import com.example.kotlin_2.screen.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val dailyRepository: DailyRepository
) : ViewModel() {
    //val allGoals : LiveData<List<GoalItem>>
    lateinit var goals: LiveData<List<GoalItem>>

    init {
        viewModelScope.launch {
            goals = goalRepository.getAllGoals()
        }
    }

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.SetGoalActive -> {
                viewModelScope.launch(Dispatchers.IO) {
                    goalRepository.setActiveGoal(event.goalItem)
                    goals = goalRepository.getAllGoals()
                }
            }

            // Maybe click on goals can be used to edit goals?
            is UIEvent.ClickOnGoal -> {
                /*add functionality what should happen when you click on goal in database*/
                viewModelScope.launch(Dispatchers.IO) {
//                    goalRepository.setActiveGoal(event.goalItem)
//
//                    event.dailyStatus.goalName = event.goalItem.name
//                    event.dailyStatus.goalSteps = event.goalItem.steps
//                    // Should we reset the currentSteps after users set a new goal?
//                    event.dailyStatus.currentSteps = 0
//                    dailyRepository.updateDaily(event.dailyStatus)
                }
            }

            is UIEvent.AddGoal -> {
                var newGoal = GoalItem(event.name, event.steps, false)
                // if there is no other goal, make that goal active
                viewModelScope.launch(Dispatchers.IO) {
                    if (goalRepository.getActiveGoal() == null) {
                        newGoal.active = true
                        //add to day status
                        if(dailyRepository.getDaily().value == null){
                            var status = DailyStatus(
                                currentSteps = 0,
                                todayDate = LocalDate.now().toString(),
                                goalName = newGoal.name,
                                goalSteps = newGoal.steps
                            )
                            dailyRepository.insertDaily(status)
                        }else {
                            var currentDaily = dailyRepository.getDaily().value
                            currentDaily!!.currentSteps = 0
                            currentDaily!!.todayDate = LocalDate.now().toString()
                            currentDaily!!.goalName = newGoal.name
                            currentDaily!!.goalSteps = newGoal.steps
                            dailyRepository.updateDaily(currentDaily)
                        }
                    }
                    goalRepository.insertGoal(newGoal)
                }
            }

            is UIEvent.DeleteGoal -> {
                viewModelScope.launch(Dispatchers.IO) {
                    goalRepository.deleteGoal(event.goalItem)
                }
            }

            is UIEvent.EditGoal -> {
                viewModelScope.launch(Dispatchers.IO) {
                    goalRepository.updateGoal(event.goalItem)
                }
            }
            else -> Unit
        }
    }


//    fun onClickOnGoal(goalItem: GoalItem, dailyStatus: DailyStatus, homeViewModel: HomeViewModel) {
//        /*add functionality what should happen when you click on goal in database*/
//        viewModelScope.launch(Dispatchers.IO) {
//            goalRepository.setActiveGoal(goalItem)
//
//            dailyStatus.goalName = goalItem.name
//            dailyStatus.goalSteps = goalItem.steps
//            homeViewModel.onGoalClick(dailyStatus)
//
//        }
//    }

//    fun onAddGoal(name: String, steps: Int, homeViewModel: HomeViewModel) {
//        var newGoal = GoalItem(name, steps, false)
//        // if there is no other goal, make that goal active
//        viewModelScope.launch(Dispatchers.IO) {
//            if (goalRepository.getActiveGoal() == null) {
//                newGoal.active = true
//                //add to day status
//                var status = DailyStatus(currentSteps = 0, todayDate = LocalDate.now().toString(), goalName = newGoal.name, goalSteps = newGoal.steps)
//                //dailyRepository.insertDaily(status)
//                homeViewModel.onAddClick(status)
//            }
//            goalRepository.insertGoal(newGoal)
//        }
//
//    }

//    fun onDeleteGoal(goalItem: GoalItem){
//        viewModelScope.launch(Dispatchers.IO) {
//            goalRepository.deleteGoal(goalItem)
//        }
//    }

//    fun onEditGoal(goalItem: GoalItem){
//        viewModelScope.launch(Dispatchers.IO) {
//            goalRepository.deleteGoal(goalItem)
//        }
//    }

}