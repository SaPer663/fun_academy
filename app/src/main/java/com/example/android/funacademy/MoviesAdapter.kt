package com.example.android.funacademy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.funacademy.databinding.ViewMovieItemBinding
import com.example.android.funacademy.model.Movie

class MoviesAdapter(private val clickListener: Listener) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ViewMovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view, clickListener, movies)
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

interface Listener {
    fun clickFragment(id: Int)
}

class MovieViewHolder(
    viewHolderMovieBinding: ViewMovieItemBinding,
    clickListener: Listener,
    movies: List<Movie>
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

    init {
        viewHolderMovieBinding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickListener.clickFragment(movies[position].id)
            }
        }
    }


    fun onBind(movie: Movie) {

        name.text = movie.title
        genre.text = movie.genres.joinToString(separator = ", ")
        Glide.with(context)
            .load(movie.imageUrl)
            .into(bgImageMovie)
        ratingBar.rating = movie.rating.toFloat()
        "${movie.reviewCount} Reviews".also { reviews.text = it }
        "${movie.runningTime}min".also { durationMin.text = it }
        "+${movie.pgAge}".also { ageRating.text = it }
        if (movie.isLiked) {
            favorites.setImageResource(R.color.pink)
        }
    }

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context