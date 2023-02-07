package com.example.kotlin_2.screen

import DataBaseHandler
import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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

        //Text(text = "Goals")

        //var goalRepository by remember {mutableStateOf(GoalRepository())}
        //var allGoals by remember {mutableStateOf(goalRepository.getAllGoals())}
        val context = LocalContext.current
        val db by remember {mutableStateOf(DataBaseHandler(context))}
        //var allGoals = db.readData()
        var allGoals by remember {mutableStateOf(db.readData())}

        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            itemsIndexed(items = allGoals) { index,
                                             goalItem ->
                Log.d("goal", index.toString())
                GoalListItem(goalItem = goalItem,
                    onClick = {
                        db.updateGoals(goalItem)
                        allGoals = db.readData()
                }
                )
            }
        }
        var stepsInput by remember { mutableStateOf(0) }
        var nameInput by remember { mutableStateOf("...") }
        var newGoal = GoalItem("new", 0, false)
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.Transparent),

            value = stepsInput.toString(),
            onValueChange = {
                stepsInput = if (it.isNotEmpty()) {
                    it.toInt()
                } else {
                    0
                }

            },
            label = { Text(text = "Add Steps for New Goal") },
            placeholder = { Text(text = "0") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = androidx.compose.ui.graphics.Color.Cyan,
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Cyan
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    })
        )

        OutlinedTextField(
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.Transparent),

            value = nameInput,
            onValueChange = {
                nameInput = if (it.isNotEmpty()) {
                    it
                } else {
                    ""
                }

            },
            label = { Text(text = "Add Name for New Goal") },
            placeholder = { Text(text = "") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = androidx.compose.ui.graphics.Color.Cyan,
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Cyan
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    //steps += stepsInput
                    //var steps = sharedPreference.getInt("currentSteps", 0)
                    newGoal.name = nameInput
                    newGoal.steps = stepsInput
                    db.insert(newGoal)
                    allGoals = db.readData()
                    focusManager.clearFocus()
                    nameInput = "..."
                    stepsInput = 0;
                })
        )
    }
}

