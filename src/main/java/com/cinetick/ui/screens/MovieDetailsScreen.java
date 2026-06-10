package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard; // Import added
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import javax.swing.*;
import java.awt.*;

public class MovieDetailsScreen extends JPanel {
    public MovieDetailsScreen(MainDashboard dashboard, String movieName, String imgUrl) {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        JPanel contentWrapper = new JPanel(new BorderLayout(50, 0));
        contentWrapper.setOpaque(false);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        // 1. LEFT: Poster
        JLabel poster = new JLabel();
        poster.setPreferredSize(new Dimension(380, 550));
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(imgUrl, 380, 550);
            SwingUtilities.invokeLater(() -> poster.setIcon(icon));
        }).start();
        contentWrapper.add(poster, BorderLayout.WEST);

        // 2. RIGHT: Info
        JPanel infoWrapper = new JPanel();
        infoWrapper.setLayout(new BoxLayout(infoWrapper, BoxLayout.Y_AXIS));
        infoWrapper.setOpaque(false);

        JLabel title = new JLabel(movieName.toUpperCase());
        title.setFont(new Font("SansSerif", Font.BOLD, 55));
        title.setForeground(Color.WHITE);

        JLabel desc = new JLabel("<html><body style='width: 500px; color: #BBBBBB;'>The world is on the brink of another revolution...</body></html>");

        // --- Buttons with Navigation ---
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 30));
        btnPanel.setOpaque(false);

        JButton playBtn = new JButton("▶ WATCH TRAILER");
        playBtn.setPreferredSize(new Dimension(200, 55));

        JButton bookBtn = new JButton("BOOK TICKETS");
        bookBtn.setBackground(Theme.PRIMARY_RED);
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setPreferredSize(new Dimension(220, 55));

       
        bookBtn.addActionListener(e -> dashboard.navigateTo("BUY_TICKETS"));

        btnPanel.add(playBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(bookBtn);

        infoWrapper.add(title);
        infoWrapper.add(Box.createRigidArea(new Dimension(0, 40)));
        infoWrapper.add(desc);
        infoWrapper.add(btnPanel);

        contentWrapper.add(infoWrapper, BorderLayout.CENTER);
        add(contentWrapper, BorderLayout.NORTH);
    }
}