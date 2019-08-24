package com.notur.submisitiga.api;

import com.notur.submisitiga.model.Movie;
import com.notur.submisitiga.model.TvShow;

import retrofit.Callback;
import retrofit.http.GET;


public interface MoviesApiService {
    @GET("/discover/movie")
    void getMovies(Callback<Movie.MovieResult> callback);

    @GET("/discover/tv")
    void getTv(Callback<TvShow.TvResult> callback);

}
