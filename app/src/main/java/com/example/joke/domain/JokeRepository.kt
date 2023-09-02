package com.example.joke.domain

import com.example.joke.data.JokeResponse

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
interface JokeRepository {
    suspend fun getNewJokes(): JokeResponse
}