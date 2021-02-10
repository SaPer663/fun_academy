package com.example.android.funacademy

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.funacademy.data.loadMovies
import com.example.android.funacademy.databinding.FragmentMoviesDetailsBinding
import com.example.android.funacademy.model.Movie
import kotlinx.coroutines.*

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

    private lateinit var actorsAdapter: ActorsAdapter
    private var tvBack: TextView? = null
    private val scope = CoroutineScope(Dispatchers.Main)
    private var fragmentMoveDetailsBinding: FragmentMoviesDetailsBinding? = null
    private val binding get() = fragmentMoveDetailsBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMoveDetailsBinding = FragmentMoviesDetailsBinding.bind(view)
        scope.launch {
            setupView()
        }
        tvBack = binding.back.apply {
            setOnClickListener { onClickBack() }
        }
    }

    override fun onDestroyView() {
        fragmentMoveDetailsBinding = null
        scope.cancel()
        super.onDestroyView()
    }

    private fun onClickBack() {
        activity?.supportFragmentManager?.apply {
            popBackStack()
        }
    }

    private fun fillViews(movie: Movie) {
        binding.apply {
            Glide
                .with(this@FragmentMoviesDetails)
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

    private suspend fun setupView() {
        val cacheMovie = obtainMovie(
            requireContext(),
            arguments?.getInt(ARGS_MOVIE) ?: 0
        )

        cacheMovie?.let {
            fillViews(it)
            if (!it.actors.isNullOrEmpty()) {
                actorsAdapter = ActorsAdapter(it.actors)
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
    }


    companion object {
        private const val ARGS_MOVIE = "ARGS_MOVIE"
        fun newInstance(id: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = bundleOf(ARGS_MOVIE to id)
            }
        }
    }

}

suspend fun obtainMovie(context: Context, id: Int) = withContext(Dispatchers.IO) {

    loadMovies(context).find { it.id == id }
}
