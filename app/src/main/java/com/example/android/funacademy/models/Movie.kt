package com.example.android.funacademy.models

data class Movie(
    val id: Int,
    val name: String,
    val picForList: Int,
    val picForDetails: Int,
    val Rating: Float,
    val numOfRatings: Int,
    val durationMin: Int,
    val ageRating: String,
    val favorite: Boolean,
    val genre: Int,
    val storyline: Int,
    val actors: List<Actor>
)
