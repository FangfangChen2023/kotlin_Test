package com.example.kotlin_2

import DataBaseHandler
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.RoundedCorner
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_2.model.BarChartViewModel
import com.example.kotlin_2.model.GoalItem
import kotlinx.coroutines.launch

/*test1*/
class MainActivity : ComponentActivity() {
    val PREFS_NAME = "currentSteps"
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSharedPreferences(PREFS_NAME, 0)
        setContent {
            Kotlin_2Theme {
                // A surface container using the 'background' color from the theme
                val context = this
                //create goal database, initialise with default goals
                val db = DataBaseHandler(context)
                //TODO delete this before actually showing the app
                //db.clearDatabase()
                val defaultGoal = GoalItem("default", 5000, true)
                //val testGoal = GoalItem("test", 3000, false)
                //db.insert(testGoal)
                //db.insert(defaultGoal)
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




