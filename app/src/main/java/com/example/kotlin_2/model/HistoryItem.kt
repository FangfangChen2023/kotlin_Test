package com.example.kotlin_2.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.horizontalbar.HorizontalBarChart
import com.himanshoe.charty.horizontalbar.model.HorizontalBarData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

data class HistoryItem(
    val date: LocalDateTime,
    var name: String,
    var steps: Int
)


//data class HistoryItem(
//    val steps: Unit =0,
//    val hisDate: String = "02/05/2023"
//        )


//class BarChartViewModel {

//    private val chartState = MutableStateFlow(Unit)
//    val uiState: StateFlow<Unit> = chartState.asStateFlow()
//    @Composable
//    fun ShowBarChart() {
//        chartState.update {
//            HorizontalBarChart(horizontalBarData = barChartData, color = Color.Gray, onBarClick = {})
//        }
//    }
//}
