package com.example.android.funacademy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.databinding.FragmentMoviesDetailsBinding
import com.example.android.funacademy.databinding.FragmentMoviesListBinding
import com.example.android.funacademy.domain.MoviesDataSource

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private lateinit var adapter: MoviesAdapter
    private var rvMovies: RecyclerView? = null
    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MoviesAdapter()
        val binding = FragmentMoviesListBinding.bind(view)
        fragmentMoviesListBinding = binding
        val recycler: RecyclerView = binding.rvMovies
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter


        rvMovies = binding.rvMovies.apply {
            setOnClickListener { clickFragment() }
        }
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.bindMovies(MoviesDataSource().getMovies())
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        super.onDestroyView()
    }

    private fun clickFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.apply {
                addToBackStack(null)
                replace<FragmentMoviesDetails>(R.id.fragments_container)
                commit()
            }
    }
}