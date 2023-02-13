package com.example.kotlin_2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GoalItem (
    var name : String,
    var steps : Int,
    var active : Boolean,
    @PrimaryKey var id:Int?=null
)