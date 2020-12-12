package com.example.android.funacademy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.android.funacademy.databinding.FragmentMoviesListBinding

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private var cardView: CardView? = null
    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesListBinding.bind(view)
        fragmentMoviesListBinding = binding
        cardView = binding.cardView.apply {
            setOnClickListener { clickFragment() }
        }
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        super.onDestroyView()
    }

    private fun clickFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.apply {
                addToBackStack(null)
                replace<FragmentMoviesDetails>(R.id.fragments_container)
                commit()
            }
    }
}