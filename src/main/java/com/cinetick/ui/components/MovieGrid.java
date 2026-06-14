package com.cinetick.ui.components;

import com.cinetick.ui.MainDashboard;
import com.cinetick.models.Movie;
import com.cinetick.service.TMDBService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MovieGrid extends JPanel {

  
    public MovieGrid(MainDashboard dashboard, String category, int limit) {
        setupUI();
        loadData(() -> TMDBService.getMovies(category, limit), dashboard);
    }

    public MovieGrid(MainDashboard dashboard, int genreId, String countryCode, int limit) {
        setupUI();
        loadData(() -> TMDBService.getFilteredMovies(genreId, countryCode, limit), dashboard);
    }
public MovieGrid(MainDashboard dashboard, String searchQuery) {
    setupUI();
   
    loadData(() -> TMDBService.searchMovies(searchQuery, 40), dashboard);
}
    private void setupUI() {
        setOpaque(false);
        setLayout(new GridLayout(0, 5, 25, 35));
        setBorder(BorderFactory.createEmptyBorder(20, 60, 50, 60));
    }

    private void loadData(DataLoader loader, MainDashboard dashboard) {
        new Thread(() -> {
            try {
                List<Movie> movies = loader.execute();
                SwingUtilities.invokeLater(() -> {
                    removeAll();
                    for (Movie m : movies) {
                        add(new MovieCard(dashboard, m.id, m.title, m.rating, m.imgUrl, m.overview));
                    }
                    revalidate();
                    repaint();
                });
            } catch (Exception e) { e.printStackTrace(); }
        }).start();
    }

    @FunctionalInterface
    public interface DataLoader {
        List<Movie> execute() throws Exception;
    }
}