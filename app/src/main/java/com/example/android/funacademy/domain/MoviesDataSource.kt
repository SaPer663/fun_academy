package com.example.android.funacademy.domain

import com.example.android.funacademy.R
import com.example.android.funacademy.models.Actor
import com.example.android.funacademy.models.Movie

class MoviesDataSource {
    private val moviesList = listOf(
        Movie(
            1, "Last Christmas", R.mipmap.pic_movie_image_in_list_last_christmas,
            R.mipmap.pic_movie_image_in_details_last_christmas,
            4.0.toFloat(), 125, 137,
            "13+", false, R.string.last_christmas_genre,
            R.string.last_christmas_storyline,
            listOf(
                Actor("Emilia Clarke", R.mipmap.pic_actor_photo_emilia_clarke),
                Actor("Madison Ingoldsby", R.mipmap.pic_actor_photo_madison_ingoldsby),
                Actor("Emma Thompson", R.mipmap.pic_actor_photo_emma_thompson),
                Actor("Boris Isakovic", R.mipmap.pic_actor_photo_boris_isakovich),
                Actor("Maxim Baldry", R.mipmap.pic_actor_photo_maxim_baldry)
            )
        ),
        Movie(
            2, "Dolittle",
            R.mipmap.pic_movie_image_in_list_dolittle,
            R.mipmap.pic_movie_image_in_details_dolittle,
            5.0.toFloat(), 98, 97,
            "16+", false, R.string.tenet_genre,
            R.string.dolitlle_storyline,
            listOf(
                Actor("Robert Downey Jr.", R.mipmap.pic_actor_photo_robert_downey_jr),
                Actor("Antonio Banderas", R.mipmap.pic_actor_photo_antonio_banderas),
                Actor("Michael Sheen", R.mipmap.pic_actor_photo_michael_sheen),
                Actor("Jim Broadbent", R.mipmap.pic_actor_photo_jim_broadbent),
                Actor("Jessie Buckley", R.mipmap.pic_actor_photo_jessie_buckley),
                Actor("Harry Colett", R.mipmap.pic_actor_photo_harry_colett)
            )
        ),
        Movie(
            3, "Underwater", R.mipmap.pic_movie_image_in_list_underwater,
            R.mipmap.pic_movie_image_in_details_underwater,
            4.0.toFloat(), 38, 102,
            "13+", true, R.string.bl_wid_genre,
            R.string.underwater_storyline,
            listOf(
                Actor("Kristen Stewart", R.mipmap.pic_actor_photo_kristen_stewart),
                Actor("Vincent Cassel", R.mipmap.pic_actor_photo_vincent_cassel),
                Actor("Mamoudou Athie", R.mipmap.pic_actor_photo_mamoudou_athie),
                Actor("T. J. Miller", R.mipmap.pic_actor_photo_tj_miller),
                Actor("John Gallagher Jr.", R.mipmap.pic_actor_photo_john_gallagher_jr),
                Actor("Jessica Henwick", R.mipmap.pic_actor_photo_jessica_henwick),
                Actor("Gunner Wright", R.mipmap.pic_actor_photo_gunner_wright),
                Actor("Fiona Rene", R.mipmap.pic_actor_photo_fiona_rene),
            )
        ),
        Movie(
            4, "The Call Of The Wild",
            R.mipmap.pic_movie_image_in_list_the_call_of_the_wild,
            R.mipmap.pic_movie_image_in_details_the_call_of_the_wild,
            5.0.toFloat(), 74, 120,
            "13+", false, R.string.the_call_of_the_wild_genre,
            R.string.the_call_of_the_wild_storyline,
            listOf(
                Actor("Harrison Ford", R.mipmap.pic_actor_photo_harrison_ford),
                Actor("Omar Sy", R.mipmap.pic_actor_photo_omar_sy),
                Actor("Cara Gee", R.mipmap.pic_actor_photo_cara_gee),
                Actor("Dan Stevens", R.mipmap.pic_actor_photo_dan_stevens),
                Actor("Bradley Whitford", R.mipmap.pic_actor_photo_bradley_whitford),
                Actor("Jean Louisa Kelly", R.mipmap.pic_actor_photo_jean_louisa_kelly)
            )
        ),
        Movie(
            5, "Avengers: End Game", R.mipmap.the_end_move_list,
            R.mipmap.background,
            4.0.toFloat(), 125, 137,
            "13+", true, R.string.tag_text,
            R.string.storyline_text,
            listOf(
                Actor("Robert Downey\u00A0Jr.", R.mipmap.iman),
                Actor("Chris Evans", R.mipmap.cap),
                Actor("Mark Ruffalo", R.mipmap.halk),
                Actor("Chris Hemsworth", R.mipmap.tor),
                Actor("Maxim Baldry", R.mipmap.pic_actor_photo_maxim_baldry)
            )
        ),
        Movie(
            6, "The Invisible Guest",
            R.mipmap.pic_movie_image_in_list_the_invisible_guest,
            R.mipmap.pic_movie_image_in_details_the_invisible_guest,
            4.toFloat(), 173, 106,
            "16+", false, R.string.the_invisible_guest_genre,
            R.string.the_invisible_guest_storyline,
            listOf(
                Actor("Mario Casas", R.mipmap.pic_actor_photo_mario_casas),
                Actor("Ana Wagener", R.mipmap.pic_actor_photo_ana_wagener),
                Actor("Barbara Lennie", R.mipmap.pic_actor_photo_barbara_lennie),
                Actor("Francesc Orella", R.mipmap.pic_actor_photo_francesc_orella),
                Actor("Paco Tous", R.mipmap.pic_actor_photo_paco_tous)
            )
        ),
        Movie(
            7, "Fantasy Island", R.mipmap.pic_movie_image_in_list_fantasy_island,
            R.mipmap.pic_movie_image_in_details_fantasy_island,
            2.4.toFloat(), 181, 109,
            "13+", false, R.string.fantasy_island_genre,
            R.string.fantasy_island_storyline,
            listOf(
                Actor("Michael Pena", R.mipmap.pic_actor_photo_michael_pena),
                Actor("Maggie Q", R.mipmap.pic_actor_photo_maggie_q),
                Actor("Lucy Hale", R.mipmap.pic_actor_photo_lucy_hale),
                Actor("Austin Stowell", R.mipmap.pic_actor_photo_austin_stowell),
                Actor("Jimmy O. Yang", R.mipmap.pic_actor_photo_jummy_o_yang),
                Actor("Portia Doubleday", R.mipmap.pic_actor_photo_portia_doubleday)
            )
        )
    )

    fun getMovieById(id: Int?) = moviesList.find { it.id == id } ?: moviesList[0]

    fun getMovies() = moviesList
}