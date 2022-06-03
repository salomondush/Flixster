package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.myapplication.adapters.MovieAdapter;
import com.example.myapplication.models.Movie;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String NOW_PLAYING_URL=
            String.format("https://api.themoviedb.org/3/movie/now_playing?api_key=%s", API_KEY);
    public static final String TAG = "MainActivity";
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        // create an adapter
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        // set the adapter on the recycler view
        rvMovies.setAdapter(movieAdapter);
        // set a layout manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        // called when response HTTP status is "200 OK"
                        Log.d(TAG, "onSuccess");
                        JSONObject jsonObject = json.jsonObject;
                        try {
                            JSONArray results = jsonObject.getJSONArray("results");
                            Log.i(TAG, "results: " + results.toString());
                            movies.addAll(Movie.fromJsonArray(results));
                            movieAdapter.notifyDataSetChanged();
                            Log.i(TAG, "Movies: " + movies.size());
                        } catch (JSONException e){
                            Log.e(TAG, "Hit json exception" , e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String errorResponse, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.d(TAG, "onFailure");
                    }
                }
        );

    }
}