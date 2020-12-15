package com.example.android.funacademy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.databinding.FragmentMoviesDetailsBinding
import com.example.android.funacademy.domain.MoviesDataSource

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
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        tvBack = binding.back.apply {
            setOnClickListener { onClickBack() }
        }
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.bindActors(MoviesDataSource().getMovies())
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

}