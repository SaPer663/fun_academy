package com.example.android.funacademy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.databinding.ViewHolderMovieBinding
import com.example.android.funacademy.models.Movie

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private lateinit var movies: List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ViewHolderMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
    }
}
class MovieViewHolder(viewHolderMovieBinding: ViewHolderMovieBinding) :
    RecyclerView.ViewHolder(viewHolderMovieBinding.root) {

    private val tag: TextView = viewHolderMovieBinding.frTag
    fun onBind(movie: Movie) {
        tag.setText(movie.genre)
    }

    }