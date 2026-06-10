package com.cinetick.ui.components;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieCard extends JPanel {
    public MovieCard(MainDashboard dashboard, String title, String rating, String imgUrl) {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);
        setPreferredSize(new Dimension(210, 320));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Poster Image
        JLabel poster = new JLabel();
        poster.setHorizontalAlignment(JLabel.CENTER);
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(imgUrl, 210, 280);
            SwingUtilities.invokeLater(() -> poster.setIcon(icon));
        }).start();

        // Info Panel
        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setOpaque(false);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setForeground(Color.WHITE);
        titleLbl.setFont(Theme.CARD_TITLE);
        
        JLabel rateLbl = new JLabel("⭐ " + rating);
        rateLbl.setForeground(Color.YELLOW);

        info.add(titleLbl);
        info.add(rateLbl);

        add(poster, BorderLayout.CENTER);
        add(info, BorderLayout.SOUTH);

     
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
             
                dashboard.openMovieDetails(title, imgUrl);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(45, 45, 45));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Theme.CARD_BG);
            }
        });
    }
}