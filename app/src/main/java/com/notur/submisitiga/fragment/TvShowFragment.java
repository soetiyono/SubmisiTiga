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
import com.notur.submisitiga.adapter.TvAdapter;
import com.notur.submisitiga.model.TvShow;

import java.util.ArrayList;


public class TvShowFragment extends Fragment {
    private TvAdapter listAdapter;
    private ProgressBar progressBar;

    public TvShowFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_tv_show, container, false);

        TvShowViewModel viewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        viewModel.getTv().observe(this,getTvShow);

        RecyclerView rvMoview = rootView.findViewById(R.id.rv_movie);
        progressBar = rootView.findViewById(R.id.progressBar);
        rvMoview.setHasFixedSize(true);

        rvMoview.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new TvAdapter(getContext());
        rvMoview.setAdapter(listAdapter);
        viewModel.getTvShow();
        showLoading(true);

        return rootView;
    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShows) {
            if (tvShows != null){
                listAdapter.setListTv(tvShows);
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
