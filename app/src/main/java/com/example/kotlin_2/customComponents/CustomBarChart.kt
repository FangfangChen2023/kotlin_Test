package com.example.kotlin_2.customComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.horizontalbar.HorizontalBarChart
import com.himanshoe.charty.horizontalbar.model.HorizontalBarData
import java.util.Date


var barChartData = listOf(
    HorizontalBarData(1000f,"02/22/2023"),
    HorizontalBarData(2000f,"02/23/2023"),
    HorizontalBarData( 3000f,"02/24/2023")
)
@Preview
@Composable
fun CustomBarChart(
//    chartData: Map<String, Int>
) {
    HorizontalBarChart(horizontalBarData = barChartData, color = Color.Gray, onBarClick = {})
//    Column(
//        modifier = Modifier
//            .padding(50.dp)
//            .fillMaxSize()
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp),
//            verticalAlignment = Alignment.Bottom,
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(2.dp)
//                    .background(Color.Gray)
//            ){
//
//            }
//
//        }
//    }


}
