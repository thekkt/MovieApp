package com.thekktim.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thekktim.movieapp.data.Movie
import com.thekktim.movieapp.databinding.MoviePostItemBinding

import com.thekktim.movieapp.network.Utils

class MovieAdapter(
    private val context: Context,
    private val movieList: MutableList<Movie>,
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    inner class MovieHolder(val binding:MoviePostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(movieList[adapterPosition])
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MoviePostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.movieTitle.text = movieList[position].title
        holder.binding.movieAbout.text = movieList[position].overview
        holder.binding.movieReleaseDate.text = movieList[position].release_date
        holder.binding.movieRating.text = movieList[position].vote_average.toString()
        Glide.with(context).load(Utils.IMAGE_URL + movieList[position].poster_path)
            .into(holder.binding.movieImage)
    }
    interface OnMovieClickListener {
        fun onItemClick(movie: Movie)
    }
    override fun getItemCount(): Int {
        return movieList.size
    }
}