package com.example.joke.presenter.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.joke.R
import com.example.joke.presenter.uistatemodel.JokeUIModel

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
class JokeAdapter(private var mList: List<JokeUIModel>) :
    RecyclerView.Adapter<JokeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_joke, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holder.jokeData.text = itemsViewModel.joke
        holder.date.text = itemsViewModel.dateTime
        if (position == 0) {
            holder.itemView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.topview)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jokeData: TextView = itemView.findViewById(R.id.jokeData)
        val date: TextView = itemView.findViewById(R.id.date)
    }
}