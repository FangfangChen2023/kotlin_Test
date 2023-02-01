package com.example.kotlin_2.customComponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.kotlin_2.repository.GoalRepository
import com.example.kotlin_2.screen.HomeScreen

var goalRepository by mutableStateOf(GoalRepository())
var activeGoal by mutableStateOf(goalRepository.getActiveGoal())
var stepGoal by mutableStateOf(activeGoal?.steps)

@Composable
fun CustomProgressBar(

    canvasSize: Dp = 250.dp,
    indicatorValue: Int = 0,
    backgroundIndicatorColor: Color = Color.Gray.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 50f,
    foregroundIndicatorColor: Color = Color.Cyan,
    foregroundIndicatorStrokeWidth: Float = 50f,

    smallText: String = "Goal: " + (activeGoal?.name),
    smallTextColor: Color = Color.Gray.copy(alpha = 0.6f),
    stepsInfoColor: Color = Color.Black,
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    stepsInfoFontSize: TextUnit = MaterialTheme.typography.h4.fontSize,

    ) {
    var maxIndicatorValue = stepGoal
    var allowedMaxIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }
    allowedMaxIndicatorValue = if (indicatorValue <= maxIndicatorValue!!) {
        indicatorValue
    } else {
        maxIndicatorValue
    }

    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedMaxIndicatorValue) {
        animatedIndicatorValue = allowedMaxIndicatorValue!!.toFloat()
    }

    val sweepAngle by animateFloatAsState(
        targetValue = ((animatedIndicatorValue / maxIndicatorValue) * 100 * 3.6).toFloat(),
        animationSpec = tween(1000)
    )
    val animatedStepsInfo by animateColorAsState(
        targetValue = if(allowedMaxIndicatorValue == 0){
            Color.Gray.copy(alpha = 0.6f)
        }else {
            stepsInfoColor
        }
    )



    //val stepsInfo = (maxIndicatorValue - allowedMaxIndicatorValue).toString()
    val stepsInfo = "$indicatorValue/$maxIndicatorValue"
    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val barSize = size / 1.25f
                backgroundIndicator(
                    indicatorColor = backgroundIndicatorColor,
                    componentSize = barSize,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )
                foregroundIndicator(
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                    componentSize = barSize,
                    sweepAngle = sweepAngle
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElementInsideBar(
            smallText = smallText,
            stepsInfo = stepsInfo,
            smallTextColor = smallTextColor,
            stepsInfoColor = animatedStepsInfo,
            smallTextFontSize = smallTextFontSize,
            stepsInfoFontSize = stepsInfoFontSize,

            )

    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 270f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.height - componentSize.height) / 2f,
            y = (size.width - componentSize.width) / 2f,
        )
    )

}

fun DrawScope.foregroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
    sweepAngle: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 270f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.height - componentSize.height) / 2f,
            y = (size.width - componentSize.width) / 2f,
        )
    )
}

@Composable
fun ElementInsideBar(
    smallText: String,
    stepsInfo: String,
    smallTextColor: Color,
    stepsInfoColor: Color,
    smallTextFontSize: TextUnit,
    stepsInfoFontSize: TextUnit
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = stepsInfo,
        color = stepsInfoColor,
        fontSize = stepsInfoFontSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}