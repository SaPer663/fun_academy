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

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list), Listener {

    private lateinit var adapter: MoviesAdapter
    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MoviesAdapter(this)
        val binding = FragmentMoviesListBinding.bind(view)
        fragmentMoviesListBinding = binding
        val recycler: RecyclerView = binding.rvMovies
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = adapter
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

    override fun clickFragment(id: Int) {
        activity?.supportFragmentManager?.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace(R.id.fragments_container, FragmentMoviesDetails.newInstance(id))
        }
    }
}