package com.cinetick.ui;

import com.cinetick.ui.components.*;
import com.cinetick.ui.screens.*;
import com.cinetick.ui.theme.Theme;
import com.cinetick.service.TMDBService;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JPanel {
    private CardLayout contentRouter;
    private JPanel centerCardPanel;
    private Sidebar sidebar;
    private VideoPlayerScreen videoPlayer;

    public MainDashboard() {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        // 1. Top Navbar
        add(new Navbar(), BorderLayout.NORTH);

        // 2. Sidebar with callback
        sidebar = new Sidebar(this::handleNavigation);
        add(sidebar, BorderLayout.WEST);

        // 3. Central Router
        contentRouter = new CardLayout();
        centerCardPanel = new JPanel(contentRouter);
        centerCardPanel.setOpaque(false);

        // 4. Embedded Video Player Initialization
        videoPlayer = new VideoPlayerScreen(this);

        // 5. Register All Production Screens
        centerCardPanel.add(wrapInScroll(new HomeView(this)), "HOME");
        centerCardPanel.add(wrapInScroll(new MoviesView(this)), "MOVIES");
        centerCardPanel.add(wrapInScroll(new TVShowsView(this)), "TV_SHOWS");
        centerCardPanel.add(wrapInScroll(new TrendingView(this)), "TRENDING");
        centerCardPanel.add(wrapInScroll(new ComingSoonView(this)), "COMING_SOON");
        centerCardPanel.add(wrapInScroll(new ShowTimesView(this)), "SHOW_TIMES");
        centerCardPanel.add(new ProfileScreen(), "PROFILE");
        centerCardPanel.add(new BookingScreen(), "BUY_TICKETS");
        centerCardPanel.add(videoPlayer, "VIDEO_PLAYER"); // Embedded Player Screen

        add(centerCardPanel, BorderLayout.CENTER);
        navigateTo("HOME");
    }

    public void navigateTo(String name) {
        contentRouter.show(centerCardPanel, name);
        if (sidebar != null) {
            String label = switch(name) {
                case "HOME" -> "Home";
                case "MOVIES" -> "Movies";
                case "TV_SHOWS" -> "TV Shows";
                case "TRENDING" -> "Trending";
                case "PROFILE" -> "Profile";
                default -> "";
            };
            if(!label.isEmpty()) sidebar.setActiveItem(label);
        }
    }

    public void openMovieDetails(int id, String title, String rating, String img, String overview) {
        JScrollPane details = wrapInScroll(new MovieDetailsScreen(this, id, title, rating, img, overview));
        centerCardPanel.add(details, "DETAILS_TEMP");
        contentRouter.show(centerCardPanel, "DETAILS_TEMP");
    }

    // --- REAL TRAILER LOGIC: Plays INSIDE the app ---
    public void playTrailer(String trailerKey) {
        if (trailerKey == null || trailerKey.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trailer not available!");
            return;
        }
        String youtubeUrl = "https://www.youtube.com/watch?v=" + trailerKey;
        contentRouter.show(centerCardPanel, "VIDEO_PLAYER");
        videoPlayer.playVideo(youtubeUrl);
    }

    private JScrollPane wrapInScroll(JPanel panel) {
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(28);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private void handleNavigation(String item) {
        switch (item) {
            case "Home" -> navigateTo("HOME");
            case "Movies" -> navigateTo("MOVIES");
            case "TV Shows" -> navigateTo("TV_SHOWS");
            case "Trending" -> navigateTo("TRENDING");
            case "Profile" -> navigateTo("PROFILE");
        }
    }
}