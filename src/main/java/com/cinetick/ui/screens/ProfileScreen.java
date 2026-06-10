package com.cinetick.ui.screens;

import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends JPanel {
    public ProfileScreen() {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        // Header Section
        JLabel title = new JLabel("Account Settings", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        add(title, BorderLayout.NORTH);

        // Center Content with Scroll (To prevent overscroll, we wrap only the card)
        JPanel container = new JPanel(new GridBagLayout());
        container.setOpaque(false);

        JPanel profileCard = new JPanel();
        profileCard.setPreferredSize(new Dimension(500, 600));
        profileCard.setBackground(new Color(25, 25, 25));
        profileCard.setLayout(new BoxLayout(profileCard, BoxLayout.Y_AXIS));
        profileCard.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Circular Avatar Placeholder
        JLabel avatar = new JLabel("👤", SwingConstants.CENTER);
        avatar.setFont(new Font("Arial", Font.PLAIN, 80));
        avatar.setForeground(Theme.PRIMARY_RED);
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel name = new JLabel("Rayhan Shorker");
        name.setFont(new Font("Arial", Font.BOLD, 22));
        name.setForeground(Color.WHITE);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel email = new JLabel("rayhan@fullstack.dev");
        email.setForeground(Color.GRAY);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);

        profileCard.add(avatar);
        profileCard.add(Box.createRigidArea(new Dimension(0, 15)));
        profileCard.add(name);
        profileCard.add(email);
        profileCard.add(Box.createRigidArea(new Dimension(0, 40)));

        // Stats Row
        profileCard.add(createStatRow("Total Tickets", "12"));
        profileCard.add(createStatRow("Favorite Genre", "Action"));
        profileCard.add(createStatRow("Member Since", "May 2026"));

        profileCard.add(Box.createVerticalGlue());
        
        JButton editBtn = new JButton("Edit Profile");
        editBtn.setBackground(Theme.PRIMARY_RED);
        editBtn.setForeground(Color.WHITE);
        editBtn.setMaximumSize(new Dimension(300, 45));
        editBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileCard.add(editBtn);

        container.add(profileCard);
        
        JScrollPane scroll = new JScrollPane(container);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel createStatRow(String label, String value) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(400, 40));
        JLabel l = new JLabel(label); l.setForeground(Color.GRAY);
        JLabel v = new JLabel(value); v.setForeground(Color.WHITE); v.setFont(new Font("Arial", Font.BOLD, 14));
        row.add(l, BorderLayout.WEST);
        row.add(v, BorderLayout.EAST);
        row.add(Box.createVerticalStrut(40));
        return row;
    }
}