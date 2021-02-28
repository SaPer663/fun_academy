package com.example.android.funacademy.features.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.funacademy.model.Movie
import com.example.android.funacademy.utils.ResourceProvider
import kotlinx.coroutines.launch

class ViewModelMovieDetails(private val resourceProvider: ResourceProvider) : ViewModel() {

    private val _stateMovieLoaded = MutableLiveData<MovieDetailsState>()
    val stateMovieLoaded: LiveData<MovieDetailsState> get() = _stateMovieLoaded

    fun loadMovie(id: Int) {
        viewModelScope.launch {
            handleMovieLoadResult(resourceProvider.loadMovieById(id))

        }
    }

    private fun handleMovieLoadResult(movie: Movie?) {
        if (movie != null) {
            _stateMovieLoaded.postValue(MovieDetailsState.MovieLoaded(movie))
        } else {
            _stateMovieLoaded.postValue(MovieDetailsState.NoMovie)
        }
    }
}