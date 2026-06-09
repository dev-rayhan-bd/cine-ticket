package com.cinetick.ui.components;

import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class Navbar extends JPanel {
    public Navbar() {
        setLayout(new BorderLayout());
        setBackground(Theme.NAVBAR_BG);
        setPreferredSize(new Dimension(1300, 80));
        setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        JLabel logo = new JLabel("CINETICK PRO");
        logo.setForeground(Theme.PRIMARY_RED);
        logo.setFont(Theme.LOGO_FONT);
        add(logo, BorderLayout.WEST);

        JPanel searchBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
        searchBox.setOpaque(false);
        
        JTextField searchField = new JTextField(20);
        searchField.setBackground(new Color(45, 45, 45));
        searchField.setForeground(Color.WHITE);
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(Theme.PRIMARY_RED);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        searchBox.add(searchField);
        searchBox.add(loginBtn);
        add(searchBox, BorderLayout.EAST);
    }
}