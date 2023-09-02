package com.example.joke.data

import com.example.joke.domain.JokeRepository
import com.example.joke.helper.Something_went_Wrong


/**
 * Created by Md Shah Hussain on 02/09/23.
 */
class JokeRepoImpl(private val api: JokeAPI) : JokeRepository {
    override suspend fun getNewJokes(): JokeResponse {
        try {
            val response = api.getNewJokes()
            val body = response.body()

            return if (response.isSuccessful && body != null && body.joke != null) {
                body
            } else {
                throw Exception()
            }
        } catch (exception: Exception) {
            throw exception.message?.let {
                CustomException(it)
            } ?: run {
                CustomException(Something_went_Wrong)
            }
        }
    }

}