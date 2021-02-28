package com.example.android.funacademy.utils

import android.content.Context
import com.example.android.funacademy.data.JsonLoad
import com.example.android.funacademy.model.Movie

class ResourceProvider(private val context: Context) : IResourceProvider {
    override suspend fun loadMovies(): List<Movie> {
        return JsonLoad(context).loadMovies()
    }

    override suspend fun loadMovieById(id: Int): Movie? {
        return JsonLoad(context).loadMovieById(id)
    }
}
