package com.example.android.funacademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.replace
import com.example.android.funacademy.features.movies.FragmentMovieList

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    replace<FragmentMovieList>(R.id.fragments_container)
                    commit()
                }
        }
    }
}
