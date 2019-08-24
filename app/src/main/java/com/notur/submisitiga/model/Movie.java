package com.notur.submisitiga.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {


    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("vote_average")
    private String rate;

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return "http://image.tmdb.org/t/p/w500" + poster;
    }

    public String getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    public String getBackdrop() {
        return "http://image.tmdb.org/t/p/w500"  + backdrop;
    }

    public static class MovieResult {
        private List<Movie> results;

        public MovieResult(List<Movie> results) {
            this.results = results;
        }

        public ArrayList<Movie> getResults() {
            return (ArrayList<Movie>) results;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.poster);
        dest.writeString(this.description);
        dest.writeString(this.backdrop);
        dest.writeString(this.rate);
    }

    private Movie(Parcel in) {
        this.title = in.readString();
        this.poster = in.readString();
        this.description = in.readString();
        this.backdrop = in.readString();
        this.rate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
