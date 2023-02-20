package com.example.kotlin_2.screen.Goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_2.data.model.GoalItem
import com.example.kotlin_2.data.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository
) : ViewModel() {
    //val allGoals : LiveData<List<GoalItem>>

     lateinit var goals : LiveData<List<GoalItem>>
    init {
        viewModelScope.launch {
              goals = goalRepository.getAllGoals()
        }
    }



    fun onClickOnGoal() {
        /*add functionality what should happen when you click on goal in database*/
    }

    fun onAddGoal(steps: Int, name: String) {
        var newGoal = GoalItem(name, steps, false)
        viewModelScope.launch(Dispatchers.IO) {
            goalRepository.insertGoal(newGoal)
        }

    }

}