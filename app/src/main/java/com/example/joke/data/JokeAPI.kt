package com.example.joke.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
interface JokeAPI {
    @GET("api")
    suspend fun getNewJokes(@Query("format") format: String = "json"): Response<JokeResponse>
}