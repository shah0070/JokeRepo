package com.example.joke.presenter.uistatemodel

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
sealed class JokeUIState {
    data class Loading(val load: String) : JokeUIState()
    data class Success(val joke: List<JokeUIModel>) : JokeUIState()
    data class Failure(val message: String) : JokeUIState()
}
