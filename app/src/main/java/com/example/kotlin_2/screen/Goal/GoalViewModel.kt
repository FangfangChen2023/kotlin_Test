package com.example.kotlin_2.screen.Goal

import androidx.lifecycle.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.kotlin_2.data.AppDatabase
import com.example.kotlin_2.data.model.GoalItem

class GoalViewModel (application: Application) : AndroidViewModel(application) {
    //val allGoals : LiveData<List<GoalItem>>
    val db = Room.databaseBuilder(application, AppDatabase::class.java, "keepFit.db").build()
    val goalDao = db.goalDao()
    var goals = goalDao.readGoals()

    fun onClickOnGoal(){
        /*add functionality what should happen when you click on goal in database*/
    }

    fun onAddGoal(steps: Int, name: String){
        var newGoal = GoalItem(name, steps, false)
        goalDao.insertGoal(newGoal)
    }

}