package com.cinetick.ui.components;

import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class MovieCard extends JPanel {
    public MovieCard(String title, String rating, String imgUrl) {
        setLayout(new BorderLayout());
        setBackground(Theme.BG_BLACK);
        setPreferredSize(new Dimension(210, 320));

        // Poster Image
        JLabel poster = new JLabel();
        poster.setHorizontalAlignment(JLabel.CENTER);
        
        poster.setBackground(new Color(40, 40, 40));
        poster.setOpaque(true);
        poster.setPreferredSize(new Dimension(210, 280));
        
        // Rounded Corner Effect (FlatLaf property)
        poster.putClientProperty("JButton.buttonType", "roundRect");

        // Info Panel
        JPanel info = new JPanel(new BorderLayout());
        info.setOpaque(false);
        info.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(Theme.CARD_TITLE);

JLabel rateLabel = new JLabel("\u2B50 " + rating);
        rateLabel.setForeground(Color.YELLOW);
        rateLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        info.add(titleLabel, BorderLayout.NORTH);
        info.add(rateLabel, BorderLayout.SOUTH);

        add(poster, BorderLayout.CENTER);
        add(info, BorderLayout.SOUTH);
    }
}