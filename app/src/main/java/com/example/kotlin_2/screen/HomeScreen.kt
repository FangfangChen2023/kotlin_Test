package com.example.kotlin_2.screen

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.kotlin_2.R
import com.example.kotlin_2.customComponents.CustomProgressBar


@Composable
fun HomeScreen() {
    //var steps = 0//by remember { mutableStateOf(0) }
    var stepsInput by remember { mutableStateOf(0) }
    val focusManager = LocalFocusManager.current


    val context = LocalContext.current
    val sharedPreference =  context.getSharedPreferences("currentSteps",Context.MODE_PRIVATE)
    val editor = sharedPreference.edit()


    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(60.dp))
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
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.Cyan
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                        onDone = {
                            //steps += stepsInput
                            var steps = sharedPreference.getInt("currentSteps", 0)
                            editor.putInt("currentSteps", steps + stepsInput);
                            editor.commit();
                            stepsInput = 0;
                            focusManager.clearFocus() })
            )

            //singleLine = true


    }
}

