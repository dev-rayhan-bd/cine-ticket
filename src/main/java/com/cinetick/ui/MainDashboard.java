package com.cinetick.ui;
import com.cinetick.ui.components.*;
import com.cinetick.ui.screens.*;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JPanel {
    private CardLayout contentRouter;
    private JPanel centerCardPanel;
    private Sidebar sidebar;
    private VideoPlayerScreen videoPlayer;
 private Navbar navbar;
    public MainDashboard() {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());
        add(new Navbar(), BorderLayout.NORTH);
        sidebar = new Sidebar(this::handleNavigation);
        add(sidebar, BorderLayout.WEST);
navbar = new Navbar();
        add(navbar, BorderLayout.NORTH);
        contentRouter = new CardLayout();
        centerCardPanel = new JPanel(contentRouter);
        centerCardPanel.setOpaque(false);

        videoPlayer = new VideoPlayerScreen(this);
        centerCardPanel.add(wrapInScroll(new HomeView(this)), "HOME");
        centerCardPanel.add(wrapInScroll(new MoviesView(this)), "MOVIES");
        centerCardPanel.add(wrapInScroll(new TVShowsView(this)), "TV_SHOWS");
        centerCardPanel.add(wrapInScroll(new TrendingView(this )), "TRENDING");
        centerCardPanel.add(new ProfileScreen(), "PROFILE");
        centerCardPanel.add(new BookingScreen(), "BUY_TICKETS");
        centerCardPanel.add(videoPlayer, "VIDEO_PLAYER");

        add(centerCardPanel, BorderLayout.CENTER);
        navigateTo("HOME");
    }
 public void refreshNavbar() {
        if (navbar != null) {
            navbar.refreshAuthUI();
        }
    }
    public void navigateTo(String name) {
        contentRouter.show(centerCardPanel, name);
        if (sidebar != null) sidebar.setActiveItem(name);
    }

    public void openMovieDetails(int id, String title, String rating, String img, String overview) {
        centerCardPanel.add(wrapInScroll(new MovieDetailsScreen(this, id, title, rating, img, overview)), "DETAILS_TEMP");
        contentRouter.show(centerCardPanel, "DETAILS_TEMP");
    }

    public void playTrailer(String key) {
        contentRouter.show(centerCardPanel, "VIDEO_PLAYER");
        videoPlayer.playVideo("https://www.youtube.com/watch?v=" + key);
    }

    private JScrollPane wrapInScroll(JPanel panel) {
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(28);
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

public void performSearch(String query) {
   
    JPanel searchResultPage = new JPanel();
    searchResultPage.setLayout(new BoxLayout(searchResultPage, BoxLayout.Y_AXIS));
    searchResultPage.setOpaque(false);

    JLabel title = new JLabel("Search Results for: " + query);
    title.setFont(Theme.TITLE_FONT);
    title.setForeground(Color.WHITE);
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

    searchResultPage.add(title);
    searchResultPage.add(new MovieGrid(this, query)); 
    searchResultPage.add(Box.createVerticalStrut(100));

    centerCardPanel.add(wrapInScroll(searchResultPage), "SEARCH_RESULTS");
    contentRouter.show(centerCardPanel, "SEARCH_RESULTS");
}



}