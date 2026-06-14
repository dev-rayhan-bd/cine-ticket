package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import com.cinetick.service.TMDBService;
import javax.swing.*;
import java.awt.*;

public class MovieDetailsScreen extends JPanel {
    private Image backdropImage;

    public MovieDetailsScreen(MainDashboard dashboard, int id, String name, String rating, String img, String overview) {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());
//(Netflix Effect)
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(img, 1920, 1080);
            if (icon != null) {
                backdropImage = icon.getImage();
                repaint();
            }
        }).start();

        JPanel mainWrapper = new JPanel(new BorderLayout(50, 0)) { // ৫-পিক্সেল গ্যাপ পোস্টার ও টেক্সটের মাঝে
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                if (backdropImage != null) {
                    g2.drawImage(backdropImage, 0, 0, getWidth(), getHeight(), null);
                }
    
                GradientPaint gp = new GradientPaint(0, 0, new Color(5, 5, 5, 180), 
                                                    0, getHeight(), new Color(5, 5, 5, 255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        mainWrapper.setOpaque(false);
        mainWrapper.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));

   
        JLabel poster = new JLabel();
        poster.setPreferredSize(new Dimension(380, 550));
        poster.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 30), 1)); // হালকা বর্ডার
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(img, 380, 550);
            SwingUtilities.invokeLater(() -> poster.setIcon(icon));
        }).start();
        mainWrapper.add(poster, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

       
        JLabel titleLbl = new JLabel(name.toUpperCase());
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 55));
        titleLbl.setForeground(Color.WHITE);
        titleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel metaLbl = new JLabel("\u2B50 " + rating + "  |  2026  |  4K Ultra HD");
        metaLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        metaLbl.setForeground(Theme.TEXT_GRAY);
        metaLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLbl = new JLabel("<html><body style='width: 450px; color: #DDDDDD; font-size: 15px; line-height: 1.6;'>" 
            + overview + "</body></html>");
        descLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

      
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 30));
        btnPanel.setOpaque(false);
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton playBtn = new JButton("\u25B6  WATCH TRAILER");
        playBtn.setPreferredSize(new Dimension(200, 50));
        playBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        playBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playBtn.addActionListener(e -> {
            new Thread(() -> {
                String key = TMDBService.getTrailerKey(id);
                dashboard.playTrailer(key);
            }).start();
        });

        JButton bookBtn = new JButton("BOOK TICKETS");
        bookBtn.setBackground(Theme.PRIMARY_RED);
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setPreferredSize(new Dimension(200, 50));
        bookBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        bookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookBtn.addActionListener(e -> dashboard.navigateTo("BUY_TICKETS"));

        btnPanel.add(playBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(bookBtn);

   
        infoPanel.add(titleLbl);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(metaLbl);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        infoPanel.add(descLbl);
        infoPanel.add(btnPanel);

        mainWrapper.add(infoPanel, BorderLayout.CENTER);

        add(mainWrapper, BorderLayout.CENTER);
    }
}