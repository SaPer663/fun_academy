package com.example.android.funacademy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.funacademy.databinding.ViewHolderActorBinding
import com.example.android.funacademy.models.Actor
import com.example.android.funacademy.models.Movie

class ActorsAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    private  var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = ViewHolderActorBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun bindActors(newMovies: List<Movie>) {
       // actors = newMovies
    }

}
class ActorViewHolder(viewHolderActorBinding: ViewHolderActorBinding) : RecyclerView.ViewHolder(viewHolderActorBinding.root) {

    private val imageActor: ImageView = viewHolderActorBinding.imageActor
    private val fullNamesActor: TextView = viewHolderActorBinding.fullNamesActor

    fun onBind(actor: Actor) {
        imageActor.setImageResource(actor.image)
        fullNamesActor.text = actor.name
    }
}