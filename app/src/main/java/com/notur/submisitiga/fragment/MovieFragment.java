package com.notur.submisitiga.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.notur.submisitiga.R;
import com.notur.submisitiga.adapter.MovieAdapter;
import com.notur.submisitiga.model.Movie;

import java.util.ArrayList;


public class MovieFragment extends Fragment {
    private MovieAdapter listAdapter;
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovie().observe(this,getMovies);

        listAdapter = new MovieAdapter(getContext());
        listAdapter.notifyDataSetChanged();

        RecyclerView rvMoview = rootView.findViewById(R.id.rv_movie);
        progressBar = rootView.findViewById(R.id.progressBar);
        rvMoview.setHasFixedSize(true);

        rvMoview.setLayoutManager(new LinearLayoutManager(getContext()));

        rvMoview.setAdapter(listAdapter);
        viewModel.getMoviesShow();
        showLoading(true);

        return rootView;
    }

    private Observer<ArrayList<Movie>>getMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null){
               listAdapter.setListMovie(movies);
               showLoading(false);
            }
        }
    };


    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
