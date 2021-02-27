package com.example.android.funacademy.utils

import com.example.android.funacademy.model.Movie

interface IResourceProvider {
    suspend fun loadMovies(): List<Movie>
}
