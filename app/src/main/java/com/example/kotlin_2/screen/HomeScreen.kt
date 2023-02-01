package com.example.kotlin_2.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_2.R
import com.example.kotlin_2.customComponents.CustomProgressBar
import com.example.kotlin_2.repository.GoalRepository

@Composable
fun HomeScreen() {
    var steps by remember { mutableStateOf(0) }
    var stepsInput by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(60.dp))
        Text(
            text = "Today",
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(30.dp))
        CustomProgressBar(
            indicatorValue = steps
        )

        Spacer(Modifier.height(30.dp))

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
                imeAction = ImeAction.Done
            ),
            singleLine = true

        )

        Spacer(Modifier.height(30.dp))

        OutlinedButton(
            modifier = Modifier
                .background(Color.Transparent),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_200)),
            onClick = {
                steps += stepsInput
                stepsInput = 0
            }
        ) {
            Text("SUBMIT", color = Color.White)
        }
    }
}

