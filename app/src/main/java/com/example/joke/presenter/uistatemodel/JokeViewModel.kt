package com.example.joke.presenter.uistatemodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joke.data.CustomException
import com.example.joke.domain.JokeUseCase
import com.example.joke.helper.ONE_MIN_TIMER
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.fixedRateTimer

const val ONE_MINUTE = 10 * 1000L

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
class JokeViewModel @ViewModelInject constructor(private val jokeUseCase: JokeUseCase) :
    ViewModel() {
    private val _jokeUIState = MutableLiveData<JokeUIState>()
    val jokeUIState: LiveData<JokeUIState> = _jokeUIState
    private var timer: Timer? = null

    init {
        timer = fixedRateTimer(period = ONE_MINUTE, initialDelay = ONE_MINUTE) {
            fetchJokes(ONE_MIN_TIMER)
        }
    }

    fun fetchJokes(status: String) = viewModelScope.launch {
        try {
            _jokeUIState.value = JokeUIState.Loading("Loading...")
            _jokeUIState.postValue(JokeUIState.Success(jokeUseCase.getNewJoke(status)))
        } catch (exception: CustomException) {
            _jokeUIState.value = JokeUIState.Failure(exception.message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}