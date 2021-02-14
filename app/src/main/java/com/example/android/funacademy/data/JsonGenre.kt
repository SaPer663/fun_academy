package com.example.android.funacademy.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class JsonGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String)