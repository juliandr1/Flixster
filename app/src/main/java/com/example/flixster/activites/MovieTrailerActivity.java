package com.example.flixster.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.flixster.R;
import com.example.flixster.databinding.ActivityMovietrailerBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.example.flixster.databinding.ActivityMovietrailerBinding;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    public static String API_KEY = "AIzaSyBwm_shJGDrXbkwZHF8k5wWfa7P1Bm07UU";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovietrailerBinding binding = ActivityMovietrailerBinding.inflate(getLayoutInflater());

        View view  = binding.getRoot();

        setContentView(view);

        // temporary test video id -- TODO replace with movie trailer video id
        final String videoId = "tKodtNFpzBA";


        // initialize with API key stored in secrets.xml
        binding.player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
    }
}
