package com.cinetick.ui;

import com.cinetick.ui.components.*;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JPanel {
    public MainDashboard() {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Theme.BG_BLACK);

        mainContent.add(new HeroBanner());

        // --- Star Cineplex Style Sub-Navbar (Filtering) ---
        JPanel filterNav = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 20));
        filterNav.setBackground(new Color(30, 10, 60)); // Cineplex Purple
        String[] filters = {"Now Showing", "Coming Soon", "Buy Tickets", "Show Times"};
        for (String f : filters) {
            JButton btn = new JButton(f);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setContentAreaFilled(false);
            btn.setBorder(null);
            filterNav.add(btn);
        }
        mainContent.add(filterNav);

        JLabel sectionTitle = new JLabel("Trending Now");
        sectionTitle.setForeground(Theme.TEXT_WHITE);
        sectionTitle.setFont(Theme.TITLE_FONT);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 0));
        mainContent.add(sectionTitle);

        JPanel movieGrid = new JPanel(new GridLayout(0, 5, 25, 35));
        movieGrid.setBackground(Theme.BG_BLACK);
        movieGrid.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        String[][] dummyMovies = {
            {"The Witcher", "9.5", "https://m.media-amazon.com/images/M/MV5BMDEwOWVlY2EtNWVjZe00MzUyLTk4MjEtOTY0Y2ZlOTkzZTEwXkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_.jpg"},
            {"The Batman", "8.9", "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg"},
            {"Joker", "9.0", "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"}
        };

        for (String[] m : dummyMovies) movieGrid.add(new MovieCard(m[0], m[1], m[2]));
        
        mainContent.add(movieGrid);
        JScrollPane scroll = new JScrollPane(mainContent);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Theme.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(240, 900));
        sidebar.setBorder(BorderFactory.createEmptyBorder(40, 25, 0, 25));

        String[] menuItems = {"Home", "TV Shows", "Movies", "Trending", "Profile"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Theme.TEXT_GRAY);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 18));
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.addActionListener(e -> {
                if (item.equals("Profile")) WindowManager.showScreen("PROFILE");
                else WindowManager.showScreen("DASHBOARD");
            });
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        return sidebar;
    }
}