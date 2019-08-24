package com.notur.submisitiga.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.notur.submisitiga.R;
import com.notur.submisitiga.model.Movie;
import com.notur.submisitiga.model.TvShow;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";
    TextView title, desc, rate;
    ImageView imgBd, img;
    CollapsingToolbarLayout toolbarLayout;
    Toolbar toolbar;
    Movie mMovie;
    TvShow mTv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            mTv = getIntent().getParcelableExtra(EXTRA_TV);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        progressBar = findViewById(R.id.progressBar);

        title = findViewById(R.id.txt_title_detail);
        desc = findViewById(R.id.txt_description_detail);
        rate = findViewById(R.id.txt_rating_detail);
        imgBd = findViewById(R.id.backdrop);
        img = findViewById(R.id.image_poster_detail);
        showLoading(true);

        if (getIntent().getParcelableExtra(EXTRA_MOVIE) != null) {
            // TODO: YOUR STATEMENT FOR MOVIE
            toolbarLayout.setTitle(mMovie.getTitle());
            title.setText(mMovie.getTitle());
            desc.setText(mMovie.getDescription());
            rate.setText(mMovie.getRate());
            Glide.with(this)
                    .load(mMovie.getBackdrop())
                    .into(imgBd);

            Glide.with(this)
                    .load(mMovie.getPoster())
                    .into(img);

            showLoading(false);

        } else {
            // TODO: YOUR STATEMENT FOR TV SHOW
            toolbarLayout.setTitle(mTv.getTitleTv());
            title.setText(mTv.getTitleTv());
            desc.setText(mTv.getDescription());
            rate.setText(mTv.getRate());
            Glide.with(this)
                    .load(mTv.getBackdropTv())
                    .into(imgBd);

            Glide.with(this)
                    .load(mTv.getPosterTv())
                    .into(img);

            showLoading(false);
        }

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
