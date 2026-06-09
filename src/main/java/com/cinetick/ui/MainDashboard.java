package com.cinetick.ui;

import com.cinetick.ui.components.*;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    public MainDashboard() {
        setTitle("CineTick Pro - Premium Movie Experience");
        setSize(1400, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        // 1. Navbar
        add(new Navbar(), BorderLayout.NORTH);

        // 2. Sidebar (Netflix Style)
        add(createSidebar(), BorderLayout.WEST);

        // 3. Main Scrollable Content
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Theme.BG_BLACK);

        // --- Premium Hero Slider ---
        mainContent.add(new HeroBanner());

        // --- Category Filter Chips ---
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 20));
        filterPanel.setOpaque(false);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));
        String[] genres = {"All", "Action", "Drama", "Sci-Fi", "Animation", "Thriller"};
        for(String g : genres) {
            filterPanel.add(new GenreChip(g));
        }
        mainContent.add(filterPanel);

        // --- Trending Section Title ---
        JLabel trendingTitle = new JLabel("Trending Now");
        trendingTitle.setForeground(Theme.TEXT_WHITE);
        trendingTitle.setFont(Theme.TITLE_FONT);
        trendingTitle.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 0));
        mainContent.add(trendingTitle);

        // --- Movie Grid (Dynamic with Images) ---
        JPanel movieGrid = new JPanel(new GridLayout(0, 5, 25, 35));
        movieGrid.setBackground(Theme.BG_BLACK);
        movieGrid.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        // Industry Standard Data Structure
        String[][] dummyMovies = {
            {"The Witcher", "9.5", "https://m.media-amazon.com/images/M/MV5BMDEwOWVlY2EtNWVjZe00MzUyLTk4MjEtOTY0Y2ZlOTkzZTEwXkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_.jpg"},
            {"Stranger Things", "9.2", "https://m.media-amazon.com/images/M/MV5BMWRiM2E2YWItY2UyOS00ZjU2LTk1MTYtMTYxMGQ2ZTBmYWU5XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_.jpg"},
            {"The Batman", "8.9", "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg"},
            {"Joker", "9.0", "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"},
            {"Spider-Man", "9.1", "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg"},
            {"Inception", "8.8", "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg"},
            {"Dark Knight", "9.0", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg"},
            {"Interstellar", "8.7", "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg"},
            {"Parasite", "8.6", "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg"},
            {"Avengers", "8.4", "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg"}
        };

        for (String[] movie : dummyMovies) {
            movieGrid.add(new MovieCard(movie[0], movie[1], movie[2]));
        }
        mainContent.add(movieGrid);

        // Custom Scroll Pane
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Theme.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(240, 900));
        sidebar.setBorder(BorderFactory.createEmptyBorder(40, 25, 0, 25));

        String[] menuItems = {"🏠 Home", "📺 TV Shows", "🎬 Movies", "🔥 Trending", "📁 My List"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Theme.TEXT_GRAY);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 18));
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        return sidebar;
    }
}