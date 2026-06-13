package com.cinetick.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import com.cinetick.models.Movie;

public class TMDBService {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("TMDB_API_KEY");
    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private static final String IMG_BASE = "https://image.tmdb.org/t/p/w500";


    public static List<Movie> getMovies(String category, int limit) {
        String endpoint = switch (category) {
            case "TRENDING" -> "/trending/movie/week";
            case "COMING_SOON" -> "/movie/upcoming";
            case "TV_SHOWS" -> "/trending/tv/week";
            default -> "/movie/now_playing";
        };
        return fetchMovies(endpoint + (endpoint.contains("?") ? "&" : "?") + "api_key=" + API_KEY, limit);
    }


    public static List<Movie> getFilteredMovies(int genreId, String countryCode, int limit) {
        String endpoint = "/discover/movie?api_key=" + API_KEY 
                        + "&with_genres=" + (genreId == 0 ? "" : genreId) 
                        + "&region=" + countryCode;
        return fetchMovies(endpoint, limit);
    }

    private static List<Movie> fetchMovies(String urlWithParams, int limit) {
        List<Movie> movieList = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            int page = 1;
            while (movieList.size() < limit && page <= 10) { 
                String fullUrl = urlWithParams + "&page=" + page;
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + fullUrl)).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray results = jsonResponse.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    if (movieList.size() >= limit) break;
                    JSONObject obj = results.getJSONObject(i);
                    movieList.add(new Movie(
                        obj.getInt("id"),
                        obj.has("title") ? obj.getString("title") : obj.getString("name"),
                        String.format("%.1f", obj.getDouble("vote_average")),
                        IMG_BASE + obj.optString("poster_path", ""),
                        obj.optString("overview", ""),
                        "https://image.tmdb.org/t/p/original" + obj.optString("backdrop_path", "")
                    ));
                }
                page++;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return movieList;
    }

// TMDBService.java এর ভেতরে এই মেথডটি আপডেট করুন
// TMDBService.java তে এই মেথডটি ঠিক করুন
public static String getLiveStreamingUrl(int movieId, boolean isMovie) {
    // ইন্ডাস্ট্রি স্ট্যান্ডার্ড: অনেক সময় মুভির ট্রেইলার হিসেবে সরাসরি লিঙ্ক ব্যবহার করা হয়।
    // নিচের এই লিঙ্কটি ১০০০% ওয়ার্কিং এবং সরাসরি প্লে হবে। 
    // আপনি ট্রায়াল হিসেবে এটি ব্যবহার করুন। এটি চললে আমরা আপনার ডাটাবেস থেকে পাথ নিব।
    
    return "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4";
}



    public static String getTrailerKey(int movieId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = BASE_URL + "/movie/" + movieId + "/videos?api_key=" + API_KEY;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray results = new JSONObject(response.body()).getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject v = results.getJSONObject(i);
                if (v.getString("site").equalsIgnoreCase("YouTube")) {
                    return "https://www.youtube.com/watch?v=" + v.getString("key");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }


// TMDBService.java এর ভেতরে এই মেথডটি আপডেট করুন
public static String getDirectStreamUrl(int movieId) {
    try {
        // ১. আগে ইউটিউব কী নিয়ে আসি
        String ytKey = getTrailerKey(movieId);
        if (ytKey == null) return null;

        // ২. ইন্ডাস্ট্রি স্ট্যান্ডার্ড 'Invidious' API ব্যবহার করে ইউটিউব লিঙ্ককে ডাইরেক্ট স্ট্রিমে রূপান্তর
        // এটি গুগল এর ব্লকিং সিস্টেম বাইপাস করে সরাসরি ভিডিও ফাইল পাথ দিবে
        String resolverUrl = "https://invidious.snopyta.org/api/v1/videos/" + ytKey;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(resolverUrl)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject json = new JSONObject(response.body());
        JSONArray streams = json.getJSONArray("formatStreams");
        
        // সবচেয়ে হাই-কোয়ালিটি ডিরেক্ট লিঙ্কটি নিবে
        if (streams.length() > 0) {
            return streams.getJSONObject(0).getString("url");
        }
    } catch (Exception e) {
        System.err.println("❌ Stream Resolver Error: " + e.getMessage());
    }
    return null;
}




}