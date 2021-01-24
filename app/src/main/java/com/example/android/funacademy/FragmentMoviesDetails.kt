package com.example.android.funacademy

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.databinding.FragmentMoviesDetailsBinding
import com.example.android.funacademy.domain.MoviesDataSource
import com.example.android.funacademy.models.Movie

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

    private lateinit var actorsAdapter: ActorsAdapter
    private var tvBack: TextView? = null
    private var fragmentMoveDetailsBinding: FragmentMoviesDetailsBinding? = null
    private val binding get() = fragmentMoveDetailsBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actorsAdapter = ActorsAdapter(this.arguments?.getInt(ARGS_MOVIE) ?: 0)
        fragmentMoveDetailsBinding = FragmentMoviesDetailsBinding.bind(view)
        val recycler: RecyclerView = binding.rvActors
        recycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = actorsAdapter
        }
        tvBack = binding.back.apply {
            setOnClickListener { onClickBack() }
        }
        fillViews(obtainFilm())
    }

    override fun onDestroyView() {
        fragmentMoveDetailsBinding = null
        super.onDestroyView()
    }

    private fun onClickBack() {
        activity?.supportFragmentManager?.apply {
            popBackStack()
        }
    }

    private fun fillViews(movie: Movie) {
        binding.apply {
            backgroundImage.setImageResource(movie.picForDetails)
            rectangl13.text = movie.ageRating
            name.text = movie.name
            tag.setText(movie.genre)
            ratingbar.rating = movie.Rating
            "${movie.numOfRatings} Reviews".also { textReviews.text = it }
            storylineName.setText(movie.storyline)
        }
    }

    private fun obtainFilm() = MoviesDataSource().getMovieById(this.arguments?.getInt(ARGS_MOVIE))

    companion object {
        private const val ARGS_MOVIE = "ARGS_MOVIE"
        fun newInstance(id: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = bundleOf(ARGS_MOVIE to id)
            }
        }
    }

}
