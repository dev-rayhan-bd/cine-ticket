package com.cinetick.models; 

public class Movie {
    public int id;
    public String title;
    public String rating;
    public String imgUrl;
    public String overview;
    public String backdropUrl;

    public Movie(int id, String title, String rating, String imgUrl, String overview, String backdropUrl) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.imgUrl = imgUrl;
        this.overview = overview;
        this.backdropUrl = backdropUrl;
    }
}