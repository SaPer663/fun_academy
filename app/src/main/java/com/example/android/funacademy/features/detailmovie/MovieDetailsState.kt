package com.example.android.funacademy.features.detailmovie

import com.example.android.funacademy.model.Movie

sealed class MovieDetailsState {

    data class MovieLoaded(val movie: Movie) : MovieDetailsState()
    object NoMovie : MovieDetailsState()
}
