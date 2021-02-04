package com.example.android.funacademy

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.funacademy.data.getListGenre
import com.example.android.funacademy.data.loadMovies
import com.example.android.funacademy.databinding.FragmentMoviesDetailsBinding
import com.example.android.funacademy.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

    private lateinit var actorsAdapter: ActorsAdapter
    private var tvBack: TextView? = null
    private val scope = CoroutineScope(Dispatchers.Main)
    private var fragmentMoveDetailsBinding: FragmentMoviesDetailsBinding? = null
    private val binding get() = fragmentMoveDetailsBinding!!
    private var cacheMovie: Movie? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMoveDetailsBinding = FragmentMoviesDetailsBinding.bind(view)
        scope.launch {
            cacheMovie = obtainMovie()
            cacheMovie?.let { fillViews(it) }
            if (cacheMovie?.actors?.isEmpty() == true) {
                binding.apply {
                    castName.visibility = View.GONE
                    rvActors.visibility = View.GONE
                }
            } else {
                actorsAdapter = ActorsAdapter(cacheMovie?.actors)
                binding.rvActors.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    adapter = actorsAdapter
                }
            }
        }
        tvBack = binding.back.apply {
            setOnClickListener { onClickBack() }
        }
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
            Glide
                .with(requireContext())
                .load(movie.imageUrl)
                .into(backgroundImage)
            "${movie.pgAge}+".also { rectangl13.text = it }
            name.text = movie.title
            tag.text = getListGenre(movie.genres).joinToString()
            ratingbar.rating = (movie.rating / 10).toFloat()
            "${movie.reviewCount} Reviews".also { textReviews.text = it }
            storyline.text = movie.storyLine
        }
    }

    private suspend fun obtainMovie() = withContext(Dispatchers.IO) {

        loadMovies(requireContext()).find { it.id == arguments?.getInt(ARGS_MOVIE) ?: 0 }
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
