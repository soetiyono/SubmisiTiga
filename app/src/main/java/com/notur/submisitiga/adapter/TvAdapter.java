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
import com.notur.submisitiga.model.TvShow;

import java.util.ArrayList;

import static com.notur.submisitiga.detail.DetailActivity.EXTRA_TV;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private ArrayList<TvShow> data = new ArrayList<>();
    public void setListTv(ArrayList<TvShow> tvShows) {
        data.clear();
        data.addAll(tvShows);
        notifyDataSetChanged();

    }
    private Context context;


    public TvAdapter(Context context) {
        this.context = context;

    }


    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,viewGroup,false);


        return new TvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.ViewHolder viewHolder, int i) {
        final TvShow tvShow = data.get(i);
        viewHolder.txtTitle.setText(tvShow.getTitleTv());
        viewHolder.txtDescription.setText(tvShow.getDescription());
        viewHolder.txtRate.setText(tvShow.getRate());

        String uri = tvShow.getPosterTv();


        Glide.with(viewHolder.itemView.getContext())
                .load(uri)
                .into(viewHolder.imgPhoto);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(EXTRA_TV,tvShow);
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
