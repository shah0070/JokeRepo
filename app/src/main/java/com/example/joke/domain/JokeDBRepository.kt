package com.example.joke.domain

import com.example.joke.room.JokeTableModel

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
interface JokeDBRepository {
    fun InsertJokeData(loginTableModel: JokeTableModel)

    fun getLatestTenJoke(): List<JokeTableModel>

    fun getLatestJoke(): List<JokeTableModel>

    fun getAllJoke(): List<JokeTableModel>

    fun deleteExtraJokes(deleteRecord: JokeTableModel)
}