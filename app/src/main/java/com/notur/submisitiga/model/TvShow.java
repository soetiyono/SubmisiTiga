package com.notur.submisitiga.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShow implements Parcelable {

    public String getTitleTv() {
        return titleTv;
    }

    public String getPosterTv() {
        return "http://image.tmdb.org/t/p/w500" + poster;
    }

    public String getDescription() {
        return description;
    }

    public String getBackdropTv() {
        return "http://image.tmdb.org/t/p/w500"  + backdrop;
    }

    public String getRate() {
        return rate;
    }

    public static class TvResult {
        private List<TvShow> results;

        public TvResult(List<TvShow> results) {
            this.results = results;
        }

        public ArrayList<TvShow> getResults() {
            return (ArrayList<TvShow>) results;
        }
    }


    @SerializedName("name")
    private String titleTv;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("vote_average")
    private String rate;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.titleTv);
        dest.writeString(this.poster);
        dest.writeString(this.description);
        dest.writeString(this.backdrop);
        dest.writeString(this.rate);
    }

    private TvShow(Parcel in) {
        this.titleTv = in.readString();
        this.poster = in.readString();
        this.description = in.readString();
        this.backdrop = in.readString();
        this.rate = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
