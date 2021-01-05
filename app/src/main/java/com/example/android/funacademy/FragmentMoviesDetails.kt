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

    private lateinit var adapter: ActorsAdapter
    private var tvBack: TextView? = null
    private var fragmentMoveDetailsBinding: FragmentMoviesDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ActorsAdapter()
        val binding = FragmentMoviesDetailsBinding.bind(view)
        fragmentMoveDetailsBinding = binding
        val recycler: RecyclerView = binding.rvActors
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recycler.adapter = adapter
        tvBack = binding.back.apply {
            setOnClickListener { onClickBack() }
        }
        val movie: Movie = MoviesDataSource().getMovieById(arguments?.getInt(ARGS_MOVIE))
        binding.backgroundImage.setImageResource(movie.picForDetails)
        binding.rectangl13.text = movie.ageRating
        binding.name.text = movie.name
        binding.tag.setText(movie.genre)
        binding.ratingbar.rating = movie.Rating
        "${movie.numOfRatings} Reviews".also { binding.textReviews.text = it }
        binding.storylineName.setText(movie.storyline)
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.bindActors(
            MoviesDataSource()
                .getMovieById(this.arguments?.getInt(ARGS_MOVIE)).actors
        )
        adapter.notifyDataSetChanged()
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

    companion object {
        private const val ARGS_MOVIE = "ARGS_MOVIE"
        fun newInstance(id: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = bundleOf(ARGS_MOVIE to id)
            }
        }
    }

}