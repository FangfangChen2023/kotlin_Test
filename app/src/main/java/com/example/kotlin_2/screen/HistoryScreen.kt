package com.example.kotlin_2.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_2.HistoryListItem
import com.example.kotlin_2.repository.HistoryRepository

@Composable
fun HistoryScreen(){
    Column (Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {

        val historyRepository = HistoryRepository()
        val allHistory = historyRepository.getAllHistory()
        
        LazyColumn(
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            itemsIndexed(items = allHistory){ index,
                historyItem ->
                Log.d("history", index.toString())
                HistoryListItem(historyItem = historyItem)
            }
        }

    }
}

