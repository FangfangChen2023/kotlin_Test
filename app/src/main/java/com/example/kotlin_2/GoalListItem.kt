package com.example.kotlin_2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_2.model.GoalItem
import com.example.kotlin_2.repository.GoalRepository

@Composable
fun GoalListItem( goalItem : GoalItem, onClick: () -> Unit){
    var color =
        if (goalItem.active){
            Color.Magenta
        } else {
            Color.Cyan
        }

    Row(
    modifier = Modifier
        .background(color)
        .fillMaxSize()
        .padding(24.dp)
        .clickable { onClick() },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = goalItem.name,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${goalItem.steps}",
            color = Color.White,
            fontSize = 30.sp
        )
    }
}