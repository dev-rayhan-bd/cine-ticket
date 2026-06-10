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

    public MainDashboard() {

        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.NORTH);

   
        sidebar = new Sidebar(this::handleNavigation);
        add(sidebar, BorderLayout.WEST);

        contentRouter = new CardLayout();
        centerCardPanel = new JPanel(contentRouter);
        centerCardPanel.setOpaque(false);

    //registering all screens in the card layout
        centerCardPanel.add(wrapInScroll(new HomeView(this)), "HOME");
centerCardPanel.add(wrapInScroll(new MoviesView(this)), "MOVIES"); 
centerCardPanel.add(wrapInScroll(new TVShowsView(this)), "TV_SHOWS"); 
centerCardPanel.add(wrapInScroll(new TrendingView(this)), "TRENDING"); 
centerCardPanel.add(wrapInScroll(new ShowTimesView(this)), "SHOW_TIMES");
centerCardPanel.add(wrapInScroll(new ComingSoonView(this)), "COMING_SOON");
        
        //profile and booking screen are not wrapped in scroll because they have their own internal scrolling if needed
        centerCardPanel.add(new ProfileScreen(), "PROFILE");
        centerCardPanel.add(new BookingScreen(), "BUY_TICKETS");

        add(centerCardPanel, BorderLayout.CENTER);

        // default homepage
        navigateTo("HOME");
    }

    /**
     *using JScrollPane for scroolling in content area
     * 
     */
private JScrollPane wrapInScroll(JPanel panel) {
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(28);
        
        // HORIZONTAL SCROLL FIX
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        return scroll;
    }

    /**
     * navigation method for changing page
     */
    public void navigateTo(String screenName) {
        contentRouter.show(centerCardPanel, screenName);
        
        //highlight sidebar
        if (sidebar != null) {
            String displayName = switch (screenName) {
                case "HOME" -> "Home";
                case "MOVIES" -> "Movies";
                case "TV_SHOWS" -> "TV Shows";
                case "TRENDING" -> "Trending";
                case "PROFILE" -> "Profile";
                default -> "";
            };
            if (!displayName.isEmpty()) sidebar.setActiveItem(displayName);
        }
    }

    /**
     * movie detaikls page open method
     */
public void openMovieDetails(String movieName, String imgUrl) {
    // 'this' (Dashboard reference) 
    JScrollPane detailsScroll = wrapInScroll(new MovieDetailsScreen(this, movieName, imgUrl));
    centerCardPanel.add(detailsScroll, "DETAILS_TEMP");
    contentRouter.show(centerCardPanel, "DETAILS_TEMP");
}

    /**
     * sidebar click handler
     */
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