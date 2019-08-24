package com.notur.submisitiga.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.notur.submisitiga.R;
import com.notur.submisitiga.detail.DetailActivity;
import com.notur.submisitiga.model.Movie;

import java.util.ArrayList;

import static com.notur.submisitiga.detail.DetailActivity.EXTRA_MOVIE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    private ArrayList<Movie>data = new ArrayList<>();
    public void setListMovie(ArrayList<Movie> movies) {
        data.clear();
        data.addAll(movies);
        notifyDataSetChanged();
    }
    private Context context;


    public MovieAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Movie movie = data.get(i);
        viewHolder.txtTitle.setText(movie.getTitle());
        viewHolder.txtDescription.setText(movie.getDescription());
        viewHolder.txtRate.setText(movie.getRate());

        String uri = movie.getPoster();

        Glide.with(viewHolder.itemView.getContext())
                .load(uri)
                .into(viewHolder.imgPhoto);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(EXTRA_MOVIE,movie);
                context.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        return (data == null)? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtDescription;
        private ImageView imgPhoto;
        private TextView txtRate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            txtDescription = itemView.findViewById(R.id.txt_description);
            imgPhoto = itemView.findViewById(R.id.image_poster);
            txtRate = itemView.findViewById(R.id.txt_rate);
        }
    }
}
