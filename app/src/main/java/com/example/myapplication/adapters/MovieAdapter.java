package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MovieDetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemMovieBinding;
import com.example.myapplication.models.Movie;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;
    ItemMovieBinding binding;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(context));
        return new ViewHolder(binding.getRoot());
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get movie based on passed in position
        Movie movie = movies.get(position);
        // Bind the movie data into the View Holder
        holder.bind(movie);
    }
    // returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // class *cannot* be static
    // implements View.OnClickListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        TextView mDate;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvTitle = binding.tvTitle;
            tvOverview = binding.tvOverview;
            ivPoster = binding.ivPoster;
            mDate = binding.mDate;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Movie movie = movies.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                // show the activity
                context.startActivity(intent);
            }
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            mDate.setText(movie.getmDate());

            String imageUrl;
            int placeHolder;

            // if in landscape, use backdrop image and placeholder image, otherwise use
            // the normal versions
            if (context.getResources().getConfiguration().orientation == Configuration.
                    ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
                placeHolder = R.drawable.flicks_backdrop_placeholder;
            } else {
                imageUrl = movie.getPosterPath();
                placeHolder = R.drawable.flicks_movie_placeholder;
            }

            int radius = 30; // corner radius, higher value = more rounded
//            int margin = 10; // crop margin, set to 0 for corners with no crop
            Glide.with(context).load(imageUrl)
                    .placeholder(placeHolder)
                    .error(R.drawable.flicks_movie_placeholder)
                    .centerCrop()
                    .transform(new RoundedCorners(radius))
                    .into(ivPoster);
        }
    }
}
