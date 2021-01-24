package com.example.android.funacademy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.databinding.ViewActorItemBinding
import com.example.android.funacademy.domain.MoviesDataSource
import com.example.android.funacademy.models.Actor

class ActorsAdapter(id: Int) : RecyclerView.Adapter<ActorViewHolder>() {

    private var actors: List<Actor> = MoviesDataSource().getMovieById(id).actors

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = ViewActorItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}

class ActorViewHolder(viewHolderActorBinding: ViewActorItemBinding) :
    RecyclerView.ViewHolder(viewHolderActorBinding.root) {

    private val imageActor: ImageView = viewHolderActorBinding.imageActor
    private val fullNamesActor: TextView = viewHolderActorBinding.fullNamesActor

    fun onBind(actor: Actor) {
        imageActor.setImageResource(actor.image)
        fullNamesActor.text = actor.name
    }
}