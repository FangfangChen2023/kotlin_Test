package com.example.kotlin_2.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_2.R
import kotlin.math.round

@Composable
fun HomeScreen(){
    var progress by remember { mutableStateOf(0.1f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value
    Column(Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(90.dp))
        Text("Today", fontSize = 30.sp)
        Spacer(Modifier.height(50.dp))
        CircularProgressIndicator(
            progress = animatedProgress,
            color = Color.Cyan,
            modifier = Modifier.then(Modifier.size(200.dp))
        )
        Spacer(Modifier.height(50.dp))
        var text by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            modifier = Modifier
                .background(Color.Transparent),
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = "Add Steps") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.Cyan),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(Modifier.height(30.dp))
        OutlinedButton(
            modifier = Modifier
                .background(Color.Transparent),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_200)),
            onClick = {
                if (progress < 1f) progress += 0.1f
            }
        ) {
            Text("SUBMIT", color = Color.White)
        }
    }
}