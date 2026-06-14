package com.cinetick.ui.components;

import com.cinetick.service.AuthSession;
import com.cinetick.ui.WindowManager;
import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

public class Navbar extends JPanel {
    private JPanel authPanel;
    private JTextField searchInput;

    public Navbar() {
        setLayout(new BorderLayout());
        setBackground(Theme.NAVBAR_BG);
        setPreferredSize(new Dimension(1300, 100)); // Standard height for high-res logo
        setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        JLabel logoLabel = new JLabel();
        try {
            URL imgUrl = getClass().getResource("/cinetick.png"); 
            if (imgUrl != null) {
            
                Image img = new ImageIcon(imgUrl).getImage().getScaledInstance(-1, 75, Image.SCALE_SMOOTH);
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
        logoLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                MainDashboard db = (MainDashboard) SwingUtilities.getAncestorOfClass(MainDashboard.class, logoLabel);
                if (db != null) db.navigateTo("HOME");
            }
        });
        add(logoLabel, BorderLayout.WEST);

        JPanel rightContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 25));
        rightContainer.setOpaque(false);

     
        JPanel searchBox = new JPanel(new BorderLayout(5, 0));
        searchBox.setOpaque(false);

        searchInput = new JTextField();
        searchInput.setPreferredSize(new Dimension(280, 42));
        searchInput.setBackground(new Color(40, 40, 40));
        searchInput.setForeground(Color.WHITE);
        searchInput.setCaretColor(Color.WHITE);
        searchInput.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70), 1),
            BorderFactory.createEmptyBorder(0, 15, 0, 10)
        ));
        // Placeholder text (FlatLaf property)
        searchInput.putClientProperty("JTextField.placeholderText", "Search movies, actors...");

        // search button with icon
        JButton searchBtn = new JButton("\uD83D\uDD0D"); // Unicode Search Icon
        searchBtn.setPreferredSize(new Dimension(50, 42));
        searchBtn.setBackground(new Color(60, 60, 60));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //  (Click & Enter key)
        searchBtn.addActionListener(e -> triggerSearch());
        searchInput.addActionListener(e -> triggerSearch());

        searchBox.add(searchInput, BorderLayout.CENTER);
        searchBox.add(searchBtn, BorderLayout.EAST);
        rightContainer.add(searchBox);

        authPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        authPanel.setOpaque(false);
        refreshAuthUI(); 

        rightContainer.add(authPanel);
        add(rightContainer, BorderLayout.EAST);
    }

 
    public void refreshAuthUI() {
        authPanel.removeAll();
        if (AuthSession.isLoggedIn()) {
         
            JLabel nameLabel = new JLabel(AuthSession.currentUser.name);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
            nameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            nameLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    MainDashboard db = (MainDashboard) SwingUtilities.getAncestorOfClass(MainDashboard.class, authPanel);
                    if (db != null) db.navigateTo("PROFILE");
                }
            });
            JLabel userPic = new JLabel();
            String picPath = AuthSession.currentUser.profilePic;
            if (picPath != null && !picPath.isEmpty() && new File(picPath).exists()) {
             
                userPic.setIcon(ImageUtil.getCircleImage(new File(picPath), 55));
            } else {
                userPic.setText("👤");
                userPic.setFont(new Font("Arial", Font.BOLD, 35));
                userPic.setForeground(Color.GRAY);
            }
            userPic.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userPic.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    MainDashboard db = (MainDashboard) SwingUtilities.getAncestorOfClass(MainDashboard.class, authPanel);
                    if (db != null) db.navigateTo("PROFILE");
                }
            });

            authPanel.add(nameLabel);
            authPanel.add(userPic);
        } else {
          
            JButton loginBtn = new JButton("Login");
            loginBtn.setBackground(Theme.PRIMARY_RED);
            loginBtn.setForeground(Color.WHITE);
            loginBtn.setPreferredSize(new Dimension(100, 42));
            loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
            loginBtn.setFocusPainted(false);
            loginBtn.addActionListener(e -> WindowManager.showScreen("LOGIN"));
            authPanel.add(loginBtn);
        }
        authPanel.revalidate();
        authPanel.repaint();
    }

  
    private void triggerSearch() {
        String query = searchInput.getText().trim();
        if (!query.isEmpty()) {
            MainDashboard db = (MainDashboard) SwingUtilities.getAncestorOfClass(MainDashboard.class, this);
            if (db != null) {
                db.performSearch(query);
            }
        }
    }
}