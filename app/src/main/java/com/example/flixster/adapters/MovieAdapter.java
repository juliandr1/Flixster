package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.activites.MovieActivity;
import com.example.flixster.models.Movie;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter (Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Inflates layout from XML and returning the holder.
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Log.d("Movie Adapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Populates data into item through the holder
    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        Log.d("Movie Adapter", "onBindViewHolder" + position);
        // Get the movie at particular position
        Movie movie = movies.get(position);
        // Bind movie to holder
        holder.bind(movie);

    }

    // Number of movies (items) present in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            ivPoster.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the row we are currently in
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION) {
                // New intent
                Intent intent = new Intent(context, MovieActivity.class);
                // Serialize the movie using parceler, using the movies shorter name
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movies.get(position)));
                // show the activity
                context.startActivity(intent);
            }
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;

            // Check whether it is in landscape or portrait
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }

            Glide.with(context).load(imageUrl).transform(new RoundedCornersTransformation(20, 10)).placeholder(R.drawable.placeholder).error(R.drawable.imagenotfound).into(ivPoster);

        }

    }


}
