package com.cinetick.ui.screens;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class MovieDetailsScreen extends JPanel {
    public MovieDetailsScreen(String movieName) {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        header.setOpaque(false);
        JButton backBtn = new JButton("← Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(e -> WindowManager.showScreen("DASHBOARD"));
        header.add(backBtn);
        add(header, BorderLayout.NORTH);

        // Content Body
        JPanel body = new JPanel(new GridBagLayout());
        body.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 40, 0, 40);

        // Left: Poster Placeholder
        JPanel poster = new JPanel();
        poster.setPreferredSize(new Dimension(350, 500));
        poster.setBackground(new Color(30,30,30));
        gbc.gridx = 0; gbc.gridy = 0;
        body.add(poster, gbc);

        // Right: Content
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.setOpaque(false);
        details.setPreferredSize(new Dimension(600, 500));

        JLabel title = new JLabel(movieName);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);

        JLabel info = new JLabel("2026 | Action | 2h 30m | ⭐ 9.0");
        info.setForeground(Theme.TEXT_GRAY);

        JLabel desc = new JLabel("<html><body style='width: 450px;'>A detailed description of " + movieName + " will be displayed here. Experience the high-octane action and deep emotional storytelling in CineTick Pro.</body></html>");
        desc.setForeground(Color.LIGHT_GRAY);
        desc.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 30));
        buttons.setOpaque(false);
        
        JButton playBtn = new JButton("▶ Watch Trailer");
        playBtn.setPreferredSize(new Dimension(180, 50));
        playBtn.setBackground(Color.WHITE);
        playBtn.setForeground(Color.BLACK);
        playBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Initializing VLCJ Player..."));

        JButton bookBtn = new JButton("Book Tickets");
        bookBtn.setPreferredSize(new Dimension(180, 50));
        bookBtn.setBackground(Theme.PRIMARY_RED);
        bookBtn.setForeground(Color.WHITE);
        bookBtn.addActionListener(e -> WindowManager.showScreen("SEAT_SELECTION"));

        buttons.add(playBtn);
        buttons.add(Box.createRigidArea(new Dimension(20, 0)));
        buttons.add(bookBtn);

        details.add(title);
        details.add(Box.createRigidArea(new Dimension(0, 10)));
        details.add(info);
        details.add(Box.createRigidArea(new Dimension(0, 30)));
        details.add(desc);
        details.add(buttons);

        gbc.gridx = 1;
        body.add(details, gbc);

        add(body, BorderLayout.CENTER);
    }
}