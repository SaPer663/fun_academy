package com.example.android.funacademy.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.funacademy.model.Movie
import com.example.android.funacademy.utils.ResourceProvider
import kotlinx.coroutines.launch

class ViewModelMovieList(private val resources: ResourceProvider) : ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movies.postValue(resources.loadMovies())
        }
    }
}
