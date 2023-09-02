package com.example.joke.presenter.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joke.databinding.ActivityMainBinding
import com.example.joke.gone
import com.example.joke.helper.RE_OPEN_APP
import com.example.joke.presenter.uistatemodel.JokeUIModel
import com.example.joke.presenter.uistatemodel.JokeUIState
import com.example.joke.presenter.uistatemodel.JokeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: JokeViewModel by viewModels()
    private var jokeList: MutableList<JokeUIModel> = arrayListOf()
    private val jokesAdapter: JokeAdapter by lazy { JokeAdapter(jokeList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
        initObserver()
        viewModel.fetchJokes(RE_OPEN_APP)
    }

    private fun initObserver() {
        viewModel.jokeUIState.observe(this) {
            when (it) {

                is JokeUIState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is JokeUIState.Success -> {
                    binding.progress.visibility = View.GONE
                    if (it.joke.size == 1) {
                        jokeList.add(0, it.joke[0])
                    } else {
                        jokeList.addAll(it.joke)
                    }
                    jokesAdapter.notifyDataSetChanged()
                }
                is JokeUIState.Failure -> {
                    binding.progress.gone()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initViews() {
        with(binding) {
            recycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = jokesAdapter
            }
        }
    }

}