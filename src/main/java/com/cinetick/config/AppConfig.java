package com.cinetick.config;

public class AppConfig {
    // UI Limits
    public static final int LIMIT_HOME = 30;
    public static final int LIMIT_MOVIES = 800;
    public static final int LIMIT_TV_SHOWS = 85;
    public static final int LIMIT_TRENDING = 15;
    public static final int LIMIT_COMING_SOON = 500;

    // TMDB Categories (Avoid strings in views)
    public static final String CAT_NOW_PLAYING = "NOW_PLAYING";
    public static final String CAT_TRENDING = "TRENDING";
    public static final String CAT_COMING_SOON = "COMING_SOON";
    public static final String CAT_TV_SHOWS = "TV_SHOWS";
    public static final String CAT_MOVIES = "MOVIES";
}