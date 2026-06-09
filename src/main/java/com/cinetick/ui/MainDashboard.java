package com.cinetick.ui;

import com.cinetick.ui.components.*;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    public MainDashboard() {
        setTitle("CineTick Pro");
        setSize(1400, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        // 1. Add Navbar
        add(new Navbar(), BorderLayout.NORTH);

        // 2. Sidebar (Netflix style)
        add(createSidebar(), BorderLayout.WEST);

        // 3. Main Content with Scroll
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Theme.BG_BLACK);

        // Hero Banner Component
        mainContent.add(new HeroBanner());

        // Trending Title
        JLabel trendingTitle = new JLabel("Trending Now");
        trendingTitle.setForeground(Theme.TEXT_WHITE);
        trendingTitle.setFont(Theme.TITLE_FONT);
        trendingTitle.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 0));
        mainContent.add(trendingTitle);

        // Movie Grid
        JPanel movieGrid = new JPanel(new GridLayout(0, 5, 25, 30));
        movieGrid.setBackground(Theme.BG_BLACK);
        movieGrid.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        // Adding 10 dummy cards for UI testing
        for (int i = 1; i <= 10; i++) {
            movieGrid.add(new MovieCard("The Dark Knight " + i, "Action | 2008", "9.0"));
        }
        mainContent.add(movieGrid);

        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Theme.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(220, 900));
        sidebar.setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));

        String[] menuItems = {"Home", "TV Shows", "Movies", "Trending", "My List"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Theme.TEXT_GRAY);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 16));
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        return sidebar;
    }
}