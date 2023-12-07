package com.adityaibrar.simple_application_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmListAdapter(private val listFilm: ArrayList<Film>) : RecyclerView.Adapter<FilmListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallBack

    interface OnItemClickCallBack {
        fun onItemClicked(film: Film)
    }

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack

    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmCover: ImageView = itemView.findViewById(R.id.img_film_cover)
        val filmTitle: TextView = itemView.findViewById(R.id.tv_film_title)
        val filmDescription: TextView = itemView.findViewById(R.id.tv_film_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.film_list, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listFilm[position]
        photo?.let { holder.filmCover.setImageResource(it) }
        holder.filmTitle.text = name
        holder.filmDescription.text = description

        holder.itemView.setOnClickListener{ this.onItemClickCallback.onItemClicked(listFilm[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int = listFilm.size
}