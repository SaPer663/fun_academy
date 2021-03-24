package com.example.android.funacademy.features.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.funacademy.utils.ResourceProvider

class ViewModelMovieDetailsFactory(private val resourceProvider: ResourceProvider) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelMovieDetails(resourceProvider) as T
    }
}
