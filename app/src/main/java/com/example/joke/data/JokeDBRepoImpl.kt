package com.example.joke.data

import com.example.joke.domain.JokeDBRepository
import com.example.joke.room.JokeDatabase
import com.example.joke.room.JokeTableModel

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
class JokeDBRepoImpl(private val jokeDatabase: JokeDatabase) : JokeDBRepository {
    override fun InsertJokeData(loginTableModel: JokeTableModel) {
        jokeDatabase.loginDao().InsertJokeData(loginTableModel)
    }

    override fun getLatestTenJoke(): List<JokeTableModel> {
        return jokeDatabase.loginDao().getLatestTenJoke()
    }

    override fun getLatestJoke(): List<JokeTableModel> {
        return jokeDatabase.loginDao().getLatestJoke()
    }

    override fun getAllJoke(): List<JokeTableModel> {
        return jokeDatabase.loginDao().getAllJoke()
    }

    override fun deleteExtraJokes(deleteRecord: JokeTableModel) {
        jokeDatabase.loginDao().deleteRecord(deleteRecord)
    }

}