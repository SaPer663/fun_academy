package com.example.android.funacademy.data

import android.content.Context
import com.example.android.funacademy.model.Actor
import com.example.android.funacademy.model.Genre
import com.example.android.funacademy.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class JsonLoad(private val context: Context) {
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    @Suppress("unused")
    suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val genresMap = loadGenres()
        val actorsMap = loadActors()

        val data = readAssetFileToString("data.json")
        parseMovies(data, genresMap, actorsMap)
    }

    private suspend fun loadGenres(): List<Genre> = withContext(Dispatchers.IO) {
        val data = readAssetFileToString("genres.json")
        parseGenres(data)
    }

    private fun readAssetFileToString(fileName: String): String {
        val stream = context.assets.open(fileName)
        return stream.bufferedReader().readText()
    }

    private fun parseGenres(jsonString: String): List<Genre> {
        val jsonGenres = jsonFormat.decodeFromString<List<JsonGenre>>(jsonString)
        return jsonGenres.map { jsonGenre -> Genre(id = jsonGenre.id, name = jsonGenre.name) }
    }

    private suspend fun loadActors(): List<Actor> = withContext(Dispatchers.IO) {
        val data = readAssetFileToString("people.json")
        parseActors(data)
    }

    private fun parseActors(jsonString: String): List<Actor> {
        val jsonActors = jsonFormat.decodeFromString<List<JsonActor>>(jsonString)
        return jsonActors.map { jsonActor ->
            Actor(
                id = jsonActor.id,
                name = jsonActor.name,
                imageUrl = jsonActor.profilePicture
            )
        }
    }

    private fun parseMovies(
        jsonString: String,
        genreData: List<Genre>,
        actors: List<Actor>
    ): List<Movie> {
        val genresMap = genreData.associateBy(Genre::id)
        val actorsMap = actors.associateBy(Actor::id)

        val jsonMovies = jsonFormat.decodeFromString<List<JsonMovie>>(jsonString)

        return jsonMovies.map { jsonMovie ->
            @Suppress("unused")
            (Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                storyLine = jsonMovie.overview,
                imageUrl = jsonMovie.posterPicture,
                detailImageUrl = jsonMovie.backdropPicture,
                rating = (jsonMovie.ratings / 2).toInt(),
                reviewCount = jsonMovie.votesCount,
                pgAge = if (jsonMovie.adult) 16 else 13,
                runningTime = jsonMovie.runtime,
                genres = jsonMovie.genreIds.map { id ->
                    genresMap[id].orThrow { IllegalArgumentException("Genre not found") }
                },
                actors = jsonMovie.actors.map { id ->
                    actorsMap[id].orThrow { IllegalArgumentException("Actor not found") }
                },
                isLiked = false
            ))
        }
    }

    suspend fun loadMovieById(id: Int) = withContext(Dispatchers.IO) {

        loadMovies().find { it.id == id }
    }

    private fun <T : Any> T?.orThrow(createThrowable: () -> Throwable): T {
        return this ?: throw createThrowable()
    }
}

