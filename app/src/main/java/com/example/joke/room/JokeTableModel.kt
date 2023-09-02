package com.example.joke.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
@Entity(tableName = "JokeTableModel")
data class JokeTableModel (

    @ColumnInfo(name = "joke")
    var joke: String,

    @ColumnInfo(name = "date")
    var date: String

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}