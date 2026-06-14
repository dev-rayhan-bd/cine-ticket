package com.cinetick.ui.components;

import com.cinetick.ui.MainDashboard;
import javax.swing.*;
import java.awt.*;

public class TabSelector extends JPanel {
    private final String[] tabs = {"Now Showing", "Coming Soon", "Buy Tickets", "Show Times"};

    public TabSelector(MainDashboard dashboard, String activeTab) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        setBackground(new Color(30, 10, 60)); // Cineplex Premium Purple
        setMaximumSize(new Dimension(2500, 70));

        for (String tab : tabs) {
            JButton btn = new JButton(tab);
            boolean isActive = tab.equals(activeTab);

       
            btn.setForeground(isActive ? Color.WHITE : Color.LIGHT_GRAY);
            btn.setFont(new Font("Arial", Font.BOLD, 15));
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            if (isActive) {
                btn.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));
                btn.setBorderPainted(true);
            }

            btn.addActionListener(e -> {
                switch (tab) {
                    case "Now Showing" -> dashboard.navigateTo("HOME");
                    case "Coming Soon" -> dashboard.navigateTo("COMING_SOON");
                    case "Show Times" -> dashboard.navigateTo("SHOW_TIMES");
                    case "Buy Tickets" -> dashboard.navigateTo("BUY_TICKETS");
                }
            });

            add(btn);
        }
    }
}