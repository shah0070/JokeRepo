package com.example.joke.domain

import com.example.joke.helper.RE_OPEN_APP
import com.example.joke.helper.Util
import com.example.joke.presenter.uistatemodel.JokeUIModel
import com.example.joke.room.JokeTableModel

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
class JokeUseCase(
    private val jokeRepository: JokeRepository,
    private val jokeDBRepository: JokeDBRepository
) {
    suspend fun getNewJoke(status: String): List<JokeUIModel> {
        val data = jokeRepository.getNewJokes().joke

        if (!data.isNullOrEmpty()) {
            jokeDBRepository.InsertJokeData(JokeTableModel(data, Util.getDate()))
            if (jokeDBRepository.getAllJoke().size > 10) {
                val deleted = jokeDBRepository.getAllJoke()[0]
                jokeDBRepository.deleteExtraJokes(deleted)
            }
        }

        if (status == RE_OPEN_APP) {
            return jokeDBRepository.getLatestTenJoke().map {
                JokeUIModel(it.joke, it.date)
            }
        } else {
            return jokeDBRepository.getLatestJoke().map {
                JokeUIModel(it.joke, it.date)
            }
        }
    }
}