package com.cinetick.ui.components;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.MainDashboard;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Navbar extends JPanel {
    public Navbar() {
        setLayout(new BorderLayout());
        setBackground(Theme.NAVBAR_BG);
        setPreferredSize(new Dimension(1300, 100)); // Height increased to 100px
        setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        // --- Logo Section ---
        JLabel logoLabel = new JLabel();
        try {
            URL imgUrl = getClass().getResource("/cinetick.png"); 
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
             
                Image img = icon.getImage().getScaledInstance(-1, 100, Image.SCALE_SMOOTH);
                logoLabel.setIcon(new ImageIcon(img));
            } else {
                logoLabel.setText("CINETICK PRO");
                logoLabel.setForeground(Theme.PRIMARY_RED);
                logoLabel.setFont(Theme.LOGO_FONT);
            }
        } catch (Exception e) {
            logoLabel.setText("CINETICK PRO");
        }
        
        logoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                MainDashboard db = (MainDashboard) SwingUtilities.getAncestorOfClass(MainDashboard.class, logoLabel);
                if (db != null) db.navigateTo("HOME");
            }
        });
        add(logoLabel, BorderLayout.WEST);

        // Right side (Search & Login)
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 30));
        rightPanel.setOpaque(false);
        
        JTextField search = new JTextField(20);
        search.setBackground(new Color(40, 40, 40));
        search.setForeground(Color.WHITE);
        search.setCaretColor(Color.WHITE);
        search.setPreferredSize(new Dimension(250, 40));
        search.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
            BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(Theme.PRIMARY_RED);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setPreferredSize(new Dimension(110, 40));
        loginBtn.setFocusPainted(false);
        loginBtn.addActionListener(e -> WindowManager.showScreen("LOGIN"));

        rightPanel.add(search);
        rightPanel.add(loginBtn);
        add(rightPanel, BorderLayout.EAST);
    }
}