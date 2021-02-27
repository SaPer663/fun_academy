package com.example.android.funacademy.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.funacademy.utils.ResourceProvider

class ViewModelMoviesListFactory(private val resource: ResourceProvider) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ViewModelMoviesList(resource) as T
}
