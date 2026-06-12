package com.cinetick.ui.screens;

import com.cinetick.config.AppConfig;
import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.components.MovieGrid;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MoviesView extends JPanel {
    private JPanel gridContainer;
    private MainDashboard dashboard;

    public MoviesView(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        setOpaque(false);

        // --- TOP FILTER BAR ---
        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        filterBar.setBackground(new Color(20, 20, 20));

        // Genres Map (TMDB standard IDs)
        String[] genres = {"All Genres", "Action", "Comedy", "Horror", "Sci-Fi", "Drama"};
        Map<String, Integer> genreMap = Map.of("All Genres", 0, "Action", 28, "Comedy", 35, "Horror", 27, "Sci-Fi", 878, "Drama", 18);
        JComboBox<String> genreBox = new JComboBox<>(genres);

        // Countries Map
        String[] countries = {"Worldwide", "USA", "India", "UK", "France"};
        Map<String, String> countryMap = Map.of("Worldwide", "", "USA", "US", "India", "IN", "UK", "GB", "France", "FR");
        JComboBox<String> countryBox = new JComboBox<>(countries);

        JButton applyBtn = new JButton("Apply Filters");
        applyBtn.setBackground(Theme.PRIMARY_RED);
        applyBtn.setForeground(Color.WHITE);

        filterBar.add(new JLabel("Genre:")); filterBar.add(genreBox);
        filterBar.add(new JLabel("Region:")); filterBar.add(countryBox);
        filterBar.add(applyBtn);
        add(filterBar, BorderLayout.NORTH);

        // --- GRID CONTAINER ---
        gridContainer = new JPanel(new BorderLayout());
        gridContainer.setOpaque(false);
        // Default Load
        updateGrid(0, ""); 
        add(gridContainer, BorderLayout.CENTER);

        applyBtn.addActionListener(e -> {
            int gId = genreMap.get(genreBox.getSelectedItem());
            String cCode = countryMap.get(countryBox.getSelectedItem());
            updateGrid(gId, cCode);
        });
        
    }

    private void updateGrid(int genreId, String countryCode) {
        gridContainer.removeAll();
    
        gridContainer.add(new MovieGrid(dashboard, genreId, countryCode, AppConfig.LIMIT_MOVIES)); 
        gridContainer.revalidate();
        gridContainer.repaint();
    }
}