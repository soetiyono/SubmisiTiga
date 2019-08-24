package com.notur.submisitiga.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.notur.submisitiga.api.MoviesApiService;
import com.notur.submisitiga.model.TvShow;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvShow>> liveData = new MutableLiveData<>();

    void getTvShow() {
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
        service.getTv(new Callback<TvShow.TvResult>() {
            @Override
            public void success(TvShow.TvResult result, Response response) {
                liveData.postValue(result.getResults());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
    LiveData<ArrayList<TvShow>> getTv(){
        return liveData;
    }
}
