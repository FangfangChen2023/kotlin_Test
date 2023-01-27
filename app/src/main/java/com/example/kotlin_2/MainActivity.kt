package com.example.kotlin_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_2.ui.theme.Kotlin_2Theme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin_2Theme {
                // A surface container using the 'background' color from the theme
               val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = TOP_LEVEL_DESTINATIONS,
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    BottomNavGraph(navController = navController)
                }
            }
        }
    }
}

//@Composable
//fun AddSteps() {
//    var progress by remember { mutableStateOf(0.1f) }
//    val animatedProgress = animateFloatAsState(
//        targetValue = progress,
//        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
//    ).value
//    Column(Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
//        Spacer(Modifier.height(80.dp))
//        Text("Total steps taken today:")
//        Spacer(Modifier.height(100.dp))
//        CircularProgressIndicator(
//            progress = animatedProgress,
//            color = Color.Red
//        )
//        Spacer(Modifier.height(120.dp))
//        var text by remember { mutableStateOf(TextFieldValue("")) }
//        TextField(
//            value = text,
//            onValueChange = {
//                text = it
//            },
//            placeholder = { Text(text = "Add Steps") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//        )
//        Spacer(Modifier.height(30.dp))
//        OutlinedButton(
//            onClick = {
//                if (progress < 1f) progress += 0.1f
//            }
//        ) {
//            Text("SUBMIT")
//        }
//    }
//}



