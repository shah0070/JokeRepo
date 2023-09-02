package com.example.joke.presenter.uistatemodel

import java.util.*

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
data class JokeUIModel(
    val joke: String?,
    val dateTime: String? = Calendar.getInstance().timeInMillis.toString(),
)
