package com.example.hellogames

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class GameAdapter(private var games: List<GameModel>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.list_item_layout_titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.list_item_layout_imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]

        Glide.with(holder.itemView)
            .load(game.urlPicture)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(game)
        }

        holder.nameTextView.text = game.name

    }

    fun updateGames(newGames: List<GameModel>) {
        games = newGames
    }

    override fun getItemCount(): Int {
        return games.size
    }

    interface OnItemClickListener {
        fun onItemClick(game: GameModel)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

}