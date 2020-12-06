package com.example.android.funacademy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private var cardView: CardView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardView = view.findViewById<CardView>(R.id.card_view).apply {
            setOnClickListener { clickFragment() }
        }
    }

    private fun clickFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.apply {
                addToBackStack(null)
                replace(R.id.fragments_container, FragmentMoviesDetails())
                commit()
            }
    }
}