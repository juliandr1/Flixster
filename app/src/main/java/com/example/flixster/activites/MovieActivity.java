package com.example.flixster.activites;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieActivity extends AppCompatActivity {

    TextView tvTitleAct, tvOverviewAct;
    ImageView ivPoster;
    RatingBar ratingBar;
    Movie movie;

    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        context = this;

        tvTitleAct = findViewById(R.id.tvTitleAct);
        tvOverviewAct = findViewById(R.id.tvOverviewAct);
        ivPoster = findViewById(R.id.ivPoster);
        ratingBar = findViewById(R.id.ratingBar);
        
        // Unwrap the movie
        movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        // set the title and overview
        tvTitleAct.setText(movie.getTitle());
        tvOverviewAct.setText(movie.getOverview());

        Log.e("Movie Activity", "Set Text");

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        ratingBar.setRating(voteAverage / 2.0f);

        String imageUrl;

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getBackdropPath();
        } else {
            imageUrl = movie.getPosterPath();
        }

        Glide.with(context).load(imageUrl).placeholder(R.drawable.placeholder).error(R.drawable.imagenotfound).into(ivPoster);

    }
}
