package com.example.flixster.activites;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.databinding.ActivityMoviesBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieActivity extends AppCompatActivity {

    Movie movie;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMoviesBinding binding = ActivityMoviesBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        context = this;

        // Unwrap the movie
        movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        Log.e("MovieActivity", movie.getTitle());
        // set the title and overview
        binding.tvTitleAct.setText(movie.getTitle());
        binding.tvOverviewAct.setText(movie.getOverview());

        Log.e("Movie Activity", "Set Text");

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        binding.ratingBar.setRating(voteAverage / 2.0f);

        String imageUrl;

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getBackdropPath();
        } else {
            imageUrl = movie.getPosterPath();
        }

        Glide.with(context).load(imageUrl).transform(new RoundedCornersTransformation(20, 10)).placeholder(R.drawable.placeholder).error(R.drawable.imagenotfound).into(binding.ivPoster);

    }
}
