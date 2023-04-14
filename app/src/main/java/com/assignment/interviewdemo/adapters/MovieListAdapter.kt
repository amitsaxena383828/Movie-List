package com.assignment.interviewdemo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.interviewdemo.R
import com.assignment.interviewdemo.constants.Constants
import com.assignment.interviewdemo.main.InterviewDemoApplication
import com.assignment.interviewdemo.main.MovieDetailActivity
import com.assignment.interviewdemo.model.MovieList
import com.assignment.interviewdemo.room.MovieDataBase
import com.assignment.interviewdemo.room.MovieTable
import com.assignment.interviewdemo.room.subscribeOnBackground
import com.bumptech.glide.Glide

class MovieListAdapter(
    var mContext: Context,
    moviesData: List<MovieList>,
    favMovieList: List<MovieTable>
) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var context: Context
    var moviesData: List<MovieList>
    var favMovieList: List<MovieTable>

    init {
        this.context = mContext
        this.moviesData = moviesData
        this.favMovieList = favMovieList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_movie, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(
            position, mContext, moviesData, favMovieList
        )
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor")
        fun bindItems(
            position: Int,
            mContext: Context,
            moviesData: List<MovieList>,
            favMovieList: List<MovieTable>
        ) {
            val cvMainContainer = itemView.findViewById(R.id.cvMainContainer) as CardView
            val ivMovieIcon = itemView.findViewById(R.id.ivMpvieIcon) as AppCompatImageView
            val ivFavIcon = itemView.findViewById(R.id.ivFavIcon) as AppCompatImageView
            val tvMovieTitle = itemView.findViewById(R.id.tvMovieName) as AppCompatTextView
            var tempPos = position
            Log.d("Adapter", "TempPos" + tempPos)
            tvMovieTitle.text = moviesData.get(position).Title
            Glide.with(mContext)
                .load(moviesData.get(position).Poster)
                .placeholder(R.drawable.ic_video)
                .into(ivMovieIcon)

            cvMainContainer.setOnClickListener {
                startDetailActivity(moviesData.get(position), mContext)
            }
            if (setFav(moviesData.get(position).imdbID, favMovieList))
                ivFavIcon.setImageTintList(ColorStateList.valueOf(R.color.red));

            ivFavIcon.setOnClickListener {

                if (ivFavIcon.imageTintList?.equals(ColorStateList.valueOf(R.color.red)) == true) {
                    ivFavIcon.setImageTintList(null)
                    subscribeOnBackground {
                        MovieDataBase.getInstance(mContext).movieDao()
                            .delete(
                                MovieTable(
                                    moviesData.get(position).Poster,
                                    moviesData.get(position).Title,
                                    moviesData.get(position).Type,
                                    moviesData.get(position).Year,
                                    moviesData.get(position).imdbID
                                )
                            )
                    }
                } else {
                    ivFavIcon.setImageTintList(ColorStateList.valueOf(R.color.red))
                    subscribeOnBackground {
                        MovieDataBase.getInstance(mContext).movieDao()
                            .insert(
                                MovieTable(
                                    moviesData.get(position).Poster,
                                    moviesData.get(position).Title,
                                    moviesData.get(position).Type,
                                    moviesData.get(position).Year,
                                    moviesData.get(position).imdbID
                                )
                            )
                    }
                }
            }
        }

        private fun startDetailActivity(movieDetail: MovieList, mContext: Context) {
            val gsonString = InterviewDemoApplication.getGsonInstance().toJson(movieDetail)
            val intent = Intent(mContext, MovieDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_PARAM_MOVIE_DETAIL, gsonString)
            mContext.startActivity(intent)
        }

        fun setFav(imdbID: String, favMovieList: List<MovieTable>): Boolean {
            for (i in 0 until favMovieList.size) {
                if (imdbID.equals(favMovieList.get(i).imdbID))
                    return true
            }
            return false

        }
    }


}

