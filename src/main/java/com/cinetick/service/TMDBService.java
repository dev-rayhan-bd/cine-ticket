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


    public static List<Movie> getFilteredMovies(int genreId, String langCode, int limit) {
         String genreParam = (genreId > 0) ? "&with_genres=" + genreId : "";
        String langParam = (langCode != null && !langCode.isEmpty()) ? "&with_original_language=" + langCode : "";
        
        String endpoint = "/discover/movie?api_key=" + API_KEY 
                        + genreParam 
                        + langParam 
                        + "&sort_by=popularity.desc";
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


public static String getLiveStreamingUrl(int movieId, boolean isMovie) {

    
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


public static String getDirectStreamUrl(int movieId) {
    try {
     
        String ytKey = getTrailerKey(movieId);
        if (ytKey == null) return null;

        String resolverUrl = "https://invidious.snopyta.org/api/v1/videos/" + ytKey;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(resolverUrl)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject json = new JSONObject(response.body());
        JSONArray streams = json.getJSONArray("formatStreams");
        
  
        if (streams.length() > 0) {
            return streams.getJSONObject(0).getString("url");
        }
    } catch (Exception e) {
        System.err.println("❌ Stream Resolver Error: " + e.getMessage());
    }
    return null;
}

public static List<Movie> searchMovies(String query, int limit) {
    // URL encoding
    String encodedQuery = java.net.URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8);
    String endpoint = "/search/movie?api_key=" + API_KEY + "&query=" + encodedQuery;
    
    return fetchMovies(endpoint, limit); 
}


}