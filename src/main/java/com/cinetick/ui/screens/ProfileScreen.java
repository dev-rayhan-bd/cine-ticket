package com.cinetick.ui.screens;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends JPanel {
    public ProfileScreen() {
        setBackground(Theme.BG_BLACK);
        setLayout(new BorderLayout());

        JButton back = new JButton("← Back");
        back.addActionListener(e -> WindowManager.goBack());
        add(back, BorderLayout.NORTH);

        JPanel profileCard = new JPanel();
        profileCard.setLayout(new GridBagLayout());
        profileCard.setBackground(new Color(30,30,30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel avatar = new JLabel("Avatar Placeholder");
        avatar.setPreferredSize(new Dimension(150, 150));
        avatar.setOpaque(true);
        avatar.setBackground(Color.DARK_GRAY);

        JLabel name = new JLabel("Rayhan Shorker");
        name.setFont(new Font("Arial", Font.BOLD, 30));
        name.setForeground(Color.WHITE);

        JLabel email = new JLabel("rayhan@fullstack.dev");
        email.setForeground(Color.GRAY);

        gbc.gridx = 0; gbc.gridy = 0;
        profileCard.add(avatar, gbc);
        gbc.gridy = 1;
        profileCard.add(name, gbc);
        gbc.gridy = 2;
        profileCard.add(email, gbc);

        add(profileCard, BorderLayout.CENTER);
    }
}