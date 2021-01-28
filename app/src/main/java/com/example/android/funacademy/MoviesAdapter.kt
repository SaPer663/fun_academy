package com.example.android.funacademy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.databinding.ViewMovieItemBinding
import com.example.android.funacademy.models.Movie

class MoviesAdapter(private val clickListener: ListenerMoviesAdapter) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ViewMovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view, clickListener)
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

interface ListenerMoviesAdapter {
    fun clickItemMovieList(id: Int)
}

class MovieViewHolder(
    viewHolderMovieBinding: ViewMovieItemBinding,
    clickListener: ListenerMoviesAdapter
) :
    RecyclerView.ViewHolder(viewHolderMovieBinding.root) {

    private val name: TextView = viewHolderMovieBinding.nameMovie
    private val genre: TextView = viewHolderMovieBinding.genre
    private val bgImageMovie: ImageView = viewHolderMovieBinding.bgImageMovie
    private val ratingBar: RatingBar = viewHolderMovieBinding.frRatingbar
    private val favorites: ImageView = viewHolderMovieBinding.favorites
    private val reviews: TextView = viewHolderMovieBinding.frTextReviews
    private val durationMin: TextView = viewHolderMovieBinding.duration
    private val ageRating: TextView = viewHolderMovieBinding.ageRating
    private var idMovie: Int = 0

    init {
        viewHolderMovieBinding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickListener.clickItemMovieList(idMovie)
            }
        }
    }

    fun onBind(movie: Movie) {

        idMovie = movie.id
        name.text = movie.name
        genre.setText(movie.genre)
        bgImageMovie.setImageResource(movie.picForList)
        ratingBar.rating = movie.Rating
        "${movie.numOfRatings} Reviews".also { reviews.text = it }
        "${movie.durationMin}min".also { durationMin.text = it }
        ageRating.text = movie.ageRating
        if (movie.favorite) {
            favorites.setImageResource(R.drawable.ic_like_favorite)
        }
    }
}