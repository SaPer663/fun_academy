package com.example.android.funacademy.features.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.funacademy.databinding.ViewActorItemBinding
import com.example.android.funacademy.model.Actor

class ActorsAdapter(private val actors: List<Actor>) : RecyclerView.Adapter<ActorViewHolder>() {

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
    private val RecyclerView.ViewHolder.context
        get() = this.itemView.context

    fun onBind(actor: Actor) {
        Glide.with(context)
            .load(actor.imageUrl)
            .into(imageActor)
        fullNamesActor.text = actor.name
    }
}