package com.example.kotlin_2.screen

import DataBaseHandler
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.BlendMode.Companion.Screen

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.kotlin_2.R
import com.example.kotlin_2.customComponents.CustomProgressBar
import com.example.kotlin_2.model.HistoryItem
import java.lang.Math.abs
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime



@Composable
fun HomeScreen() {
    //var steps = 0//by remember { mutableStateOf(0) }
    var stepsInput by remember { mutableStateOf(0) }
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val sharedPreference =  context.getSharedPreferences("currentSteps",Context.MODE_PRIVATE)
    val sharedPreferenceDay =  context.getSharedPreferences("date",Context.MODE_PRIVATE)
    val sharedPreferenceGoal =  context.getSharedPreferences("goal",Context.MODE_PRIVATE)
    val editor = sharedPreference.edit()
    val editorDay = sharedPreferenceDay.edit()
    val editorGoal = sharedPreferenceGoal.edit()

    TopAppBar(
        title = { Text("iWalk") },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Settings, contentDescription = "Localized description")
            }
        }
    )

                Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(Modifier.height(75.dp))
                    Text(
                        text = "Today",
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(30.dp))
                    CustomProgressBar(
                        indicatorValue = sharedPreference.getInt("currentSteps", 0)//steps
                    )

                    Spacer(Modifier.height(30.dp))
                    //TextField(value = "Progress: " +"h"+ "%" , onValueChange = "h" )

                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .background(Color.Transparent),

                        value = stepsInput.toString(),
                        onValueChange = {
                            stepsInput = if (it.isNotEmpty()) {
                                it.toInt()
                            } else {
                                0
                            }

                        },
                        label = { Text(text = "Add Steps") },
                        placeholder = { Text(text = "0") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Blue
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                //steps += stepsInput
                                var steps = sharedPreference.getInt("currentSteps", 0)
                                editor.putInt("currentSteps", steps + stepsInput);
                                editor.commit();

                                var currentDate = LocalDateTime.now()
                                val oldDate = sharedPreferenceDay.getString("date", null)
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
                                    val db = DataBaseHandler(context)
                                    //var activeGoal = db.getActiveGoal()
                                    var goal = sharedPreferenceGoal.getString("goal", null)
                                    if (goal == null) {
                                        goal = "default"
                                    }
                                    var steps = sharedPreference.getInt("currentSteps", 0)
                                    //Toast.makeText(context, "something happened at least,$dateMemorised, $goal, $steps", Toast.LENGTH_SHORT).show()
                                    val history = HistoryItem(
                                        dateMemorised,
                                        goal,
                                        sharedPreference.getInt("currentSteps", 0)
                                    )
                                    db.insertDayStatus(history)
                                    //var histories = db.readHistory()
                                    editor.putInt("currentSteps", 0);
                                    editorGoal.putString("name", goal);
                                    editor.commit()
                                    editorGoal.commit()
                                    //Toast.makeText(context, "something happened at least,$history, $histories", Toast.LENGTH_SHORT).show()
                                }
                                editorDay.putString("date", currentDate.toString());
                                editorDay.commit()
                                //var date = sharedPreferenceDay.getString("date", null)
                                //var curdate = currentDate.toString()
                                //Toast.makeText(context, "commited current date, $curdate, $date", Toast.LENGTH_SHORT).show()
                                stepsInput = 0;
                                focusManager.clearFocus()
                            })

                    )

                    //singleLine = true


                }
            }