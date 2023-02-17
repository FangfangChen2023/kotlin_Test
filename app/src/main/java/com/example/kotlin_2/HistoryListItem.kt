package com.example.kotlin_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.kotlin_2.data.model.GoalItem
import com.example.kotlin_2.data.model.HistoryItem
import com.example.kotlin_2.data.repository.GoalRepository
import java.time.format.DateTimeFormatter

@Composable
fun HistoryListItem(historyItem : HistoryItem, onClick: () -> Unit){
    var color =
            Color.Transparent


    Row(
        modifier = Modifier
            .border(1.dp, Color.Blue,
            shape = RoundedCornerShape(12.dp))
            .background(color,
            shape = RoundedCornerShape(12.dp))
            .fillMaxSize()
            .padding(24.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = historyItem.date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = historyItem.name,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = historyItem.steps.toString(),
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}


//package com.example.kotlin_2
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.kotlin_2.model.HistoryItem
//
//
//@Composable
//fun HistoryListItem( historyItem : HistoryItem ){
//    Row(
//        modifier = Modifier
//            .background(Color.Cyan)
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        Text(
//            text = historyItem.hisDate,
//            color = Color.White,
//            fontSize = 30.sp,
//            fontWeight = FontWeight.Bold
//        )
//        Text(
//            text = "${historyItem.steps}",
//            color = Color.White,
//            fontSize = 30.sp
//        )
//    }
//}