package com.example.joke.room

import androidx.room.*


/**
 * Created by Md Shah Hussain on 02/09/23.
 */
@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertJokeData(loginTableModel: JokeTableModel)

    @Query("SELECT * FROM JokeTableModel ORDER BY id DESC LIMIT 10")
    fun getLatestTenJoke() : List<JokeTableModel>

    @Query("SELECT * FROM JokeTableModel ORDER BY id DESC LIMIT 1")
    fun getLatestJoke() : List<JokeTableModel>

    @Query("SELECT * FROM JokeTableModel ORDER BY id ASC")
    fun getAllJoke() : List<JokeTableModel>

    @Delete
    fun deleteRecord(record:JokeTableModel)

}