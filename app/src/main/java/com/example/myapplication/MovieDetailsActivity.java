package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.myapplication.databinding.ActivityMovieDetailsBinding;
import com.example.myapplication.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    // the movie to display
    Movie movie;

    // the view objects
    TextView tvTitle;
    TextView tvOverview;
    TextView mDate;
    RatingBar rbVoteAverage;
    ImageView viewPoster;
    ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        // layout of activity is stored in a special property called root
        View view = binding.getRoot();
        setContentView(view);

        // resolve the view objects
        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        rbVoteAverage = binding.rbVoteAverage;
        viewPoster = binding.viewPoster;
        mDate = binding.mDate;

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and the overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        mDate.setText(movie.getmDate());

        // convert rating from a 10 scale to a 5 scale
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);

        String imageUrl;
        int placeHolder;

        // if in landscape, use backdrop image and placeholder image, otherwise use
        // the normal versions
        if (MovieDetailsActivity.this.getResources().getConfiguration().orientation == Configuration.
                ORIENTATION_LANDSCAPE){
            imageUrl = movie.getBackdropPath();
            placeHolder = R.drawable.flicks_backdrop_placeholder;
        } else {
            imageUrl = movie.getPosterPath();
            placeHolder = R.drawable.flicks_movie_placeholder;
        }

        int radius = 30; // corner radius, higher value = more rounded
        Glide.with(MovieDetailsActivity.this).load(imageUrl)
                .placeholder(placeHolder)
                .error(R.drawable.flicks_movie_placeholder)
                .centerCrop()
                .transform(new RoundedCorners(radius))
                .into(viewPoster);
    }
}