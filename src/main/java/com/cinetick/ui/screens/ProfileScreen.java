package com.cinetick.ui.screens;

import com.cinetick.service.AuthSession;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProfileScreen extends JPanel {
    private JPanel card;

    public ProfileScreen() {
        setBackground(Theme.BG_BLACK);
        setLayout(new GridBagLayout());

     
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                renderProfile();
            }
        });
    }

    private void renderProfile() {
        removeAll(); 
        
        if (!AuthSession.isLoggedIn()) {
            add(new JLabel("<html><h2 style='color:white;'>Please Login first.</h2></html>"));
            return;
        }

        card = new JPanel();
        card.setPreferredSize(new Dimension(500, 650));
        card.setBackground(new Color(25, 25, 25));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // --- profile image---
        JLabel avatar = new JLabel();
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatar.setPreferredSize(new Dimension(160, 160));
        
        String picPath = AuthSession.currentUser.profilePic;
        if (picPath != null && !picPath.isEmpty()) {
            File imgFile = new File(picPath);
            if(imgFile.exists()) {
                avatar.setIcon(ImageUtil.fromFile(imgFile, 160, 160));
            } else {
                avatar.setText("👤");
                avatar.setFont(new Font("Arial", Font.PLAIN, 100));
            }
        } else {
            avatar.setText("👤");
            avatar.setFont(new Font("Arial", Font.PLAIN, 100));
        }

        JLabel nameLbl = new JLabel(AuthSession.currentUser.name);
        nameLbl.setFont(new Font("Arial", Font.BOLD, 30));
        nameLbl.setForeground(Color.WHITE);
        nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLbl = new JLabel(AuthSession.currentUser.email);
        emailLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLbl.setForeground(Color.GRAY);
        emailLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- dammy status ---
        JPanel stats = new JPanel(new GridLayout(1, 3, 20, 0));
        stats.setOpaque(false);
        stats.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        stats.add(createStatItem("Tickets", "12"));
        stats.add(createStatItem("Reviews", "5"));
        stats.add(createStatItem("Points", "450"));

        // --- action buttons ---
        JButton logoutBtn = new JButton("LOGOUT ACCOUNT");
        logoutBtn.setBackground(Theme.PRIMARY_RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setMaximumSize(new Dimension(300, 50));
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(e -> {
            AuthSession.logout();
            com.cinetick.ui.WindowManager.showScreen("LOGIN");
        });

       
        card.add(avatar);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(nameLbl);
        card.add(emailLbl);
        card.add(stats);
        card.add(Box.createVerticalGlue());
        card.add(logoutBtn);

        add(card);
        revalidate();
        repaint();
    }

    private JPanel createStatItem(String label, String value) {
        JPanel p = new JPanel(new GridLayout(2, 1));
        p.setOpaque(false);
        JLabel v = new JLabel(value, SwingConstants.CENTER);
        v.setFont(new Font("Arial", Font.BOLD, 18));
        v.setForeground(Color.WHITE);
        JLabel l = new JLabel(label, SwingConstants.CENTER);
        l.setForeground(Color.GRAY);
        p.add(v); p.add(l);
        return p;
    }
}