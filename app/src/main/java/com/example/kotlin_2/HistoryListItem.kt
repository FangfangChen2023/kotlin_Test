package com.example.kotlin_2
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