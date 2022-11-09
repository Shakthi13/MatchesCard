package com.example.matchescard.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {

    @Insert
    suspend fun insertAll(Characters: List<Person>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: Person): Int

    @Query("select * from Person")
    fun getAll(): LiveData<List<Person>>

    @Query("SELECT * FROM Person WHERE id = :id")
    fun getPerson(id:Int):LiveData<Person>

}