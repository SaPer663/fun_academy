package com.example.android.funacademy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val detailImageUrl: String,
    val rating: Int,
    val reviewCount: Int,
    val runningTime: Int,
    val pgAge: Int,
    val isLiked: Boolean,
    val genres: List<Genre>,
    val storyLine: String,
    val actors: List<Actor>
) : Parcelable
