package com.cinetick.ui.components;

import com.cinetick.ui.WindowManager;
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
        logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) { WindowManager.showScreen("DASHBOARD"); }
        });
        add(logo, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
        rightPanel.setOpaque(false);
        
        JTextField search = new JTextField(20);
        search.setBackground(new Color(45, 45, 45));
        search.setForeground(Color.WHITE);
        search.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(Theme.PRIMARY_RED);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(e -> WindowManager.showScreen("LOGIN"));

        rightPanel.add(search);
        rightPanel.add(loginBtn);
        add(rightPanel, BorderLayout.EAST);
    }
}