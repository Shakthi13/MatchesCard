package com.example.matchescard.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name:String,
    val age: String?,
    val gender:String,
    val imageUrl:String,
    val imageUrlBig:String,
    val location:String,
    val country:String,
    val phoneNo:String,
    var clickedOption:String = ""
)
