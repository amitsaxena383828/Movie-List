package com.assignment.interviewdemo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.interviewdemo.R
import com.assignment.interviewdemo.model.MovieList
import com.bumptech.glide.Glide

class MovieListAdapter(var mContext: Context, moviesData: List<MovieList>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var context: Context
    var moviesData: List<MovieList>

    init {
        this.context = mContext
        this.moviesData = moviesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_movie, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(
            position, mContext, moviesData
        )
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor")
        fun bindItems(position: Int, mContext: Context, moviesData: List<MovieList>) {
            val cvMainContainer = itemView.findViewById(R.id.cvMainContainer) as CardView
            val ivMovieIcon = itemView.findViewById(R.id.ivMpvieIcon) as AppCompatImageView
            val ivFavIcon = itemView.findViewById(R.id.ivFavIcon) as AppCompatImageView
            val tvMovieTitle = itemView.findViewById(R.id.tvMovieName) as AppCompatTextView
            tvMovieTitle.text = moviesData.get(position).Title
            Glide.with(mContext)
                .load(moviesData.get(position).Poster)
                .placeholder(R.drawable.ic_video)
                .into(ivMovieIcon)

            cvMainContainer.setOnClickListener {

            }

            ivFavIcon.setOnClickListener {
                ivFavIcon.setImageTintList(ColorStateList.valueOf(R.color.red));
            }
        }
    }

}

