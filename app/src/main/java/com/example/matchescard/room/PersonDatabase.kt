package com.example.matchescard.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase:RoomDatabase() {
    abstract fun PersonDao():PersonDao
}