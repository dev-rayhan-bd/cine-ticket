package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import com.cinetick.service.TMDBService;
import javax.swing.*;
import java.awt.*;

public class MovieDetailsScreen extends JPanel {
    public MovieDetailsScreen(MainDashboard dashboard, int id, String name, String rating, String img, String overview) {
        setBackground(Theme.BG_BLACK);
        setLayout(new GridBagLayout()); 
        setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // 1. LEFT: Movie Poster
        JLabel poster = new JLabel();
        poster.setPreferredSize(new Dimension(380, 550));
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(img, 380, 550);
            SwingUtilities.invokeLater(() -> poster.setIcon(icon));
        }).start();
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, 0, 50); // Small gap between poster and text
        add(poster, gbc);

        // 2. RIGHT: Content Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel titleLbl = new JLabel(name.toUpperCase());
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 55));
        titleLbl.setForeground(Color.WHITE);

        JLabel metaLbl = new JLabel("\u2B50 " + rating + "  |  2026  |  Action, Sci-Fi  |  4K");
        metaLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        metaLbl.setForeground(Theme.TEXT_GRAY);

        // Fixed width for description to prevent horizontal scroll
        JLabel descLbl = new JLabel("<html><body style='width: 500px; color: #BBBBBB; font-size: 15px;'>" + overview + "</body></html>");

        // Action Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 30));
        btnPanel.setOpaque(false);

        JButton playBtn = new JButton("\u25B6 WATCH TRAILER");
        playBtn.setPreferredSize(new Dimension(220, 55));
        playBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        playBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // playBtn.addActionListener(e -> {
        //     new Thread(() -> {
        //         String key = TMDBService.getTrailerUrl(id);
        //         dashboard.playTrailer(key);
        //     }).start();
        // });
playBtn.addActionListener(e -> {
    // ১. TMDB ID টি নিন
    int tmdbId = id; 
    
    // ২. স্ট্রিমিং ইউআরএল জেনারেট করুন (ইউটিউব ছাড়া)
    String streamUrl = TMDBService.getLiveStreamingUrl(tmdbId, false);
    
    // ৩. প্লেয়ারে পাঠিয়ে দিন
    dashboard.playTrailer(streamUrl); // এখন এটি সরাসরি স্ট্রীম প্লে করবে
});
        JButton bookBtn = new JButton("BOOK TICKETS");
        bookBtn.setBackground(Theme.PRIMARY_RED);
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setPreferredSize(new Dimension(220, 55));
        bookBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        bookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookBtn.addActionListener(e -> dashboard.navigateTo("BUY_TICKETS"));

        btnPanel.add(playBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        btnPanel.add(bookBtn);

        infoPanel.add(titleLbl);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(metaLbl);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        infoPanel.add(descLbl);
        infoPanel.add(btnPanel);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(infoPanel, gbc);
    }
}