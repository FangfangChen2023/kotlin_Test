package com.example.kotlin_2.screen

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.kotlin_2.repository.GoalRepository
import com.example.kotlin_2.GoalListItem
import com.example.kotlin_2.model.GoalItem


@SuppressLint("UnrememberedMutableState")
@Composable
fun GoalScreen(){
    Column (modifier = Modifier
        .fillMaxSize()
        ,horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Goals")

        var goalRepository by remember {mutableStateOf(GoalRepository())}
        var allGoals by remember {mutableStateOf(goalRepository.getAllGoals())}

        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            itemsIndexed(items = allGoals) { index,
                                             goalItem ->
                Log.d("goal", index.toString())
                GoalListItem(goalItem = goalItem, onClick = {
                    goalRepository = GoalRepository()
                    goalRepository.setActiveGoal(goalItem)
                })
            }
        }
    }
}

