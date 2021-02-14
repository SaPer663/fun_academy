package com.example.android.funacademy.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class JsonMovie(
    @SerialName("id")
    val id: Int,
    @SerialName("original_title")
    val title: String,
    @SerialName("poster_path")
    val posterPicture: String,
    @SerialName("backdrop_path")
    val backdropPicture: String,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("actors")
    val actors: List<Int>,
    @SerialName("vote_average")
    val ratings: Float,
    @SerialName("vote_count")
    val votesCount: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("adult")
    val adult: Boolean
)