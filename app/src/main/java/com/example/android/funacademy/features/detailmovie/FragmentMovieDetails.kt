package com.example.android.funacademy.features.detailmovie

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.funacademy.R
import com.example.android.funacademy.databinding.FragmentMoviesDetailsBinding
import com.example.android.funacademy.model.Movie
import com.example.android.funacademy.utils.ResourceProvider
import kotlinx.coroutines.*

class FragmentMovieDetails : Fragment(R.layout.fragment_movies_details) {

    private lateinit var actorsAdapter: ActorsAdapter
    private var tvBack: TextView? = null
    private var fragmentMoveDetailsBinding: FragmentMoviesDetailsBinding? = null
    private val binding get() = fragmentMoveDetailsBinding!!
    private lateinit var viewModel: ViewModelMovieDetails

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMoveDetailsBinding = FragmentMoviesDetailsBinding.bind(view)
        val movieId = arguments?.getInt(ARGS_MOVIE) ?: 0
        val resourceProvider = ResourceProvider(requireContext())
        val viewModelMovieDetailsFactory = ViewModelMovieDetailsFactory(resourceProvider)
        viewModel = ViewModelProvider(
            this,
            viewModelMovieDetailsFactory
        ).get(ViewModelMovieDetails::class.java)
        viewModel.apply {
            loadMovie(movieId)
            stateMovieLoaded.observe(viewLifecycleOwner, { state ->
                when (state) {
                    is MovieDetailsState.MovieLoaded -> setupView(state.movie)
                    MovieDetailsState.NoMovie -> showErrorToast()
                }
            })
        }
        tvBack = binding.back.apply {
            setOnClickListener { onClickBack() }
        }
    }

    override fun onDestroyView() {
        fragmentMoveDetailsBinding = null
        super.onDestroyView()
    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            "No movie found with this ID",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onClickBack() {
        activity?.supportFragmentManager?.apply {
            popBackStack()
        }
    }

    private fun fillViews(movie: Movie) {
        binding.apply {
            Glide
                .with(this@FragmentMovieDetails)
                .load(movie.imageUrl)
                .into(backgroundImage)
            "${movie.pgAge}+".also { rectangl13.text = it }
            name.text = movie.title
            tag.text = movie.genres.joinToString { it.name }
            ratingbar.rating = (movie.rating / 10).toFloat()
            "${movie.reviewCount} Reviews".also { textReviews.text = it }
            storyline.text = movie.storyLine

        }
    }

    private fun setupView(movie: Movie) {

        fillViews(movie)
        if (!movie.actors.isNullOrEmpty()) {
            actorsAdapter = ActorsAdapter(movie.actors)
            binding.apply {
                castName.visibility = View.VISIBLE
                rvActors.visibility = View.VISIBLE
                rvActors.apply {
                    layoutManager =
                        LinearLayoutManager(
                            requireContext(),
                            RecyclerView.HORIZONTAL,
                            false
                        )
                    adapter = actorsAdapter
                }
            }
        }

    }

    companion object {
        private const val ARGS_MOVIE = "ARGS_MOVIE"
        fun newInstance(id: Int): FragmentMovieDetails {
            return FragmentMovieDetails().apply {
                arguments = bundleOf(ARGS_MOVIE to id)
            }
        }
    }
}
