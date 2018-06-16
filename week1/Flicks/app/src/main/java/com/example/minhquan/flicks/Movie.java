package com.example.minhquan.flicks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Movie  {
    /**
     * The movie id
     **/
    protected int id;
    /**
     * The movie title
     **/
    protected String title;
    /**
     * The movie original title
     **/
    protected String original_title;
    /**
     * The movie release date
     **/
    protected String release_date;
    /**
     * The movie rating
     **/
    protected double rating;
    /**
     * The movie popularity
     **/
    protected double popularity;
    /**
     * The movie poster image
     **/
    protected String poster;

    /**
     * The movie backdrop image
     **/
    protected String backdrop;

    /**
     * The movie overview
     */
    protected String overview;

    public Movie(int id, String title, String original_title, String release_date,
                 String poster, String backdrop, double rating, double popularity, String overview) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster = poster;
        this.backdrop = backdrop;
        this.rating = rating;
        this.popularity = popularity;
        this.overview = overview;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public double getRating() {
        return rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster() {
        return poster;
    }

    public long whenIsBeingReleased() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(this.release_date);
            return date.getTime() - Calendar.getInstance().getTime().getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}
