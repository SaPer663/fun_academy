package com.example.android.funacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.funacademy.databinding.FragmentMoviesDetailsBinding

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

    private var tvBack: TextView? = null
    private var fragmentMoveDetailsBinding: FragmentMoviesDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesDetailsBinding.bind(view)
        fragmentMoveDetailsBinding = binding
        tvBack = binding.back.apply {
            setOnClickListener { onClickBack() }
        }
    }

    override fun onDestroyView() {
        fragmentMoveDetailsBinding = null
        super.onDestroyView()
    }

    private fun onClickBack() {
        activity?.supportFragmentManager?.apply {
            popBackStack()
        }
    }

}