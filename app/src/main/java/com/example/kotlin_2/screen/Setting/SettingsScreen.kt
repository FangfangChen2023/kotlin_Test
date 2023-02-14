package com.example.kotlin_2.screen.Setting

import DataBaseHandler
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.BlendMode.Companion.Screen

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.kotlin_2.R
import com.example.kotlin_2.customComponents.CustomProgressBar
import com.example.kotlin_2.data.model.HistoryItem
import com.example.kotlin_2.screen.Home.HomeViewModel
import java.lang.Math.abs
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime



@Composable
fun SettingsScreen() {



    /*TopAppBar(
        title = { Text("iWalk") },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Settings, contentDescription = "Localized description")
            }
        }
    )*/


    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(75.dp))
        Text(
            text = "Settings",
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(30.dp))






    }
}