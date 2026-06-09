package com.cinetick.ui.components;

import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import javax.swing.*;
import java.awt.*;

public class MovieCard extends JPanel {
    public MovieCard(String title, String rating, String imgUrl) {
        setLayout(new BorderLayout());
        setBackground(Theme.BG_BLACK);
        setPreferredSize(new Dimension(210, 340));

        // Poster Image Container
        JLabel poster = new JLabel();
        poster.setOpaque(true);
        poster.setBackground(new Color(30, 30, 30));
        
        // Load Image from URL
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(imgUrl, 210, 280);
            SwingUtilities.invokeLater(() -> poster.setIcon(icon));
        }).start();

        // Details Panel
        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setOpaque(false);
        info.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setForeground(Color.WHITE);
        titleLbl.setFont(Theme.CARD_TITLE);
        
        JLabel rateLbl = new JLabel("\u2B50 " + rating);
        rateLbl.setForeground(Color.YELLOW);

        info.add(titleLbl);
        info.add(rateLbl);

        add(poster, BorderLayout.CENTER);
        add(info, BorderLayout.SOUTH);
    }
}