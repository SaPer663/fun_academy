package com.example.android.funacademy

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.data.loadMovies
import com.example.android.funacademy.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list), ListenerMoviesAdapter {

    private lateinit var moviesAdapter: MoviesAdapter
    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    private val binding get() = fragmentMoviesListBinding!!
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter = MoviesAdapter(this)
        fragmentMoviesListBinding = FragmentMoviesListBinding.bind(view)
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = moviesAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        scope.launch{ context?.let { updateData(it) } }
    }

    private suspend fun updateData(context: Context) = withContext(Dispatchers.IO) {
        adapter.bindMovies(loadMovies(context))
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        super.onDestroyView()
    }

    override fun clickItemMovieList(id: Int) {
        activity?.supportFragmentManager?.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace(R.id.fragments_container, FragmentMoviesDetails.newInstance(id))
        }
    }
}