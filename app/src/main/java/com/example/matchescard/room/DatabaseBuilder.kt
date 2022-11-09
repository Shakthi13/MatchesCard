package com.example.matchescard.room

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
        private var INSTANCE: PersonDatabase? = null
        fun getInstance(context: Context): PersonDatabase {
            if (INSTANCE == null) {
                synchronized(PersonDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PersonDatabase::class.java,
                "MatchPerson"
            ).build()


}