package com.example.kotlin_2.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GoalScreen(){
    Column (Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Goal Screen")

    }
}