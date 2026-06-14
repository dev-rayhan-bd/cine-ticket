// package com.cinetick.ui.screens;

// import com.cinetick.config.AppConfig;
// import com.cinetick.ui.MainDashboard;
// import com.cinetick.ui.components.MovieGrid;
// import com.cinetick.ui.theme.Theme;
// import javax.swing.*;
// import java.awt.*;
// import java.util.Map;

// public class MoviesView extends JPanel {
//     private JPanel gridContainer;
//     private MainDashboard dashboard;

//     public MoviesView(MainDashboard dashboard) {
//         this.dashboard = dashboard;
//         setLayout(new BorderLayout());
//         setOpaque(false);

//         // --- TOP FILTER BAR ---
//         JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
//         filterBar.setBackground(new Color(20, 20, 20));

//         // Genres Map (TMDB standard IDs)
//         String[] genres = {"All Genres", "Action", "Comedy", "Horror", "Sci-Fi", "Drama"};
//         Map<String, Integer> genreMap = Map.of("All Genres", 0, "Action", 28, "Comedy", 35, "Horror", 27, "Sci-Fi", 878, "Drama", 18);
//         JComboBox<String> genreBox = new JComboBox<>(genres);

//         // Countries Map
//         String[] countries = {"Worldwide", "USA", "India", "UK", "France"};
//         Map<String, String> countryMap = Map.of("Worldwide", "", "USA", "US", "India", "IN", "UK", "GB", "France", "FR");
//         JComboBox<String> countryBox = new JComboBox<>(countries);

//         JButton applyBtn = new JButton("Apply Filters");
//         applyBtn.setBackground(Theme.PRIMARY_RED);
//         applyBtn.setForeground(Color.WHITE);

//         filterBar.add(new JLabel("Genre:")); filterBar.add(genreBox);
//         filterBar.add(new JLabel("Region:")); filterBar.add(countryBox);
//         filterBar.add(applyBtn);
//         add(filterBar, BorderLayout.NORTH);

//         // --- GRID CONTAINER ---
//         gridContainer = new JPanel(new BorderLayout());
//         gridContainer.setOpaque(false);
//         // Default Load
//         updateGrid(0, ""); 
//         add(gridContainer, BorderLayout.CENTER);

//         applyBtn.addActionListener(e -> {
//             int gId = genreMap.get(genreBox.getSelectedItem());
//             String cCode = countryMap.get(countryBox.getSelectedItem());
//             updateGrid(gId, cCode);
//         });
        
//     }

//     private void updateGrid(int genreId, String countryCode) {
//         gridContainer.removeAll();
    
//         gridContainer.add(new MovieGrid(dashboard, genreId, countryCode, AppConfig.LIMIT_MOVIES)); 
//         gridContainer.revalidate();
//         gridContainer.repaint();
//     }
// }
package com.cinetick.ui.screens;

import com.cinetick.config.AppConfig;
import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.components.MovieGrid;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoviesView extends JPanel {
    private JPanel gridContainer;
    private MainDashboard dashboard;

    public MoviesView(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        setOpaque(false);

        // --- PREMIUM NETFLIX-STYLE FILTER BAR ---
        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 25));
        filterBar.setBackground(Theme.BG_BLACK);
        filterBar.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

      
        Map<String, Integer> genreMap = new LinkedHashMap<>();
        genreMap.put("All Genres", 0);
        genreMap.put("Action", 28);
        genreMap.put("Comedy", 35);
        genreMap.put("Horror", 27);
        genreMap.put("Sci-Fi", 878);
        genreMap.put("Drama", 18);
        genreMap.put("Animation", 16);
        genreMap.put("Thriller", 53);
        genreMap.put("Romance", 10749);

        JComboBox<String> genreBox = createPremiumCombo(genreMap.keySet().toArray(new String[0]));

       
        Map<String, String> countryMap = new LinkedHashMap<>();
        countryMap.put("Worldwide", "");
        countryMap.put("Bangladesh \uD83C\uDDE7\uD83C\uDDE9", "BD"); // BD Flag Emoji
        countryMap.put("USA", "US");
        countryMap.put("India", "IN");
        countryMap.put("South Korea", "KR");
        countryMap.put("Japan", "JP");
        countryMap.put("United Kingdom", "GB");

        JComboBox<String> countryBox = createPremiumCombo(countryMap.keySet().toArray(new String[0]));


        JButton applyBtn = new JButton("EXPLORE");
        applyBtn.setPreferredSize(new Dimension(130, 42));
        applyBtn.setBackground(Theme.PRIMARY_RED);
        applyBtn.setForeground(Color.WHITE);
        applyBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        applyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        applyBtn.putClientProperty("JButton.buttonType", "roundRect"); // FlatLaf Rounded

        applyBtn.addActionListener(e -> {
            int gId = genreMap.get(genreBox.getSelectedItem());
            String cCode = countryMap.get(countryBox.getSelectedItem());
            updateGrid(gId, cCode);
        });

        JLabel gLbl = new JLabel("Genre:"); gLbl.setForeground(Color.GRAY);
        JLabel rLbl = new JLabel("Region:"); rLbl.setForeground(Color.GRAY);
        
        filterBar.add(gLbl); filterBar.add(genreBox);
        filterBar.add(Box.createHorizontalStrut(10));
        filterBar.add(rLbl); filterBar.add(countryBox);
        filterBar.add(Box.createHorizontalStrut(20));
        filterBar.add(applyBtn);

        add(filterBar, BorderLayout.NORTH);

        // --- GRID CONTAINER ---
        gridContainer = new JPanel(new BorderLayout());
        gridContainer.setOpaque(false);
        updateGrid(0, ""); // Default Load
        add(gridContainer, BorderLayout.CENTER);
    }

  
    private JComboBox<String> createPremiumCombo(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setPreferredSize(new Dimension(180, 42));
        combo.setBackground(new Color(35, 35, 35)); // Dark background
        combo.setForeground(Color.WHITE);
        combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // FlatLaf Styling
        combo.putClientProperty("JComponent.roundRect", true);
        combo.putClientProperty("JComboBox.isSquare", false);
        combo.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        
        return combo;
    }

    private void updateGrid(int genreId, String countryCode) {
        gridContainer.removeAll();
        gridContainer.add(new MovieGrid(dashboard, genreId, countryCode, AppConfig.LIMIT_MOVIES)); 
        gridContainer.revalidate();
        gridContainer.repaint();
    }
}