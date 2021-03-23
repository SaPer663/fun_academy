package com.example.android.funacademy.features.movies

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.funacademy.R
import com.example.android.funacademy.databinding.FragmentMoviesListBinding
import com.example.android.funacademy.features.detailmovie.FragmentMovieDetails
import com.example.android.funacademy.utils.ResourceProvider

class FragmentMovieList : Fragment(R.layout.fragment_movies_list), ListenerMoviesAdapter {

    private lateinit var moviesAdapter: MoviesAdapter
    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    private val binding get() = fragmentMoviesListBinding!!
    private lateinit var viewModel: ViewModelMovieList
    private var resourceProvider: ResourceProvider? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        resourceProvider = ResourceProvider(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter = MoviesAdapter(this)
        fragmentMoviesListBinding = FragmentMoviesListBinding.bind(view)
        val viewModelFactory = ViewModelMovieListFactory(resourceProvider)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModelMovieList::class.java)
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = moviesAdapter
        }
        loadData()
    }

    private fun loadData() {
        viewModel.movies.observe(viewLifecycleOwner, { movieList ->
            moviesAdapter.bindMovies(movieList)
        })
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        super.onDestroyView()
    }

    override fun clickItemMovieList(id: Int) {
        activity?.supportFragmentManager?.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace(R.id.fragments_container, FragmentMovieDetails.newInstance(id))
        }
    }
}
