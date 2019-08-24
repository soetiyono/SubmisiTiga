package com.notur.submisitiga.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import com.notur.submisitiga.api.MoviesApiService;
import com.notur.submisitiga.model.Movie;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>>liveData = new MutableLiveData<>();

    void getMoviesShow() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "c71dd2c44c015379b1db2def1574c482");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        MoviesApiService service = restAdapter.create(MoviesApiService.class);
        service.getMovies(new Callback<Movie.MovieResult>() {

            @Override
            public void success(Movie.MovieResult movieResult, Response response) {
                liveData.postValue(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }
    MutableLiveData<ArrayList<Movie>> getMovie(){
        return liveData;
    }
}
