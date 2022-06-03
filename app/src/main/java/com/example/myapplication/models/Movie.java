package com.example.myapplication.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    String mDate;
    Double voteAverage;

    // no-arg, empty constructor required for Parceler
    public Movie(){}

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        mDate = jsonObject.getString("release_date");
        voteAverage = jsonObject.getDouble("vote_average");
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); ++i){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342" + posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w342" + backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getmDate() {
        return mDate;
    }
}
