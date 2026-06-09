package com.cinetick;

import com.cinetick.ui.MainDashboard;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // FlatLaf Dark Theme Setup
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            // UI Polishing: Rounded corners
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("ScrollBar.width", 10);
            UIManager.put("ScrollBar.thumbArc", 10);
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF");
        }

        SwingUtilities.invokeLater(() -> {
            MainDashboard dashboard = new MainDashboard();
            dashboard.setVisible(true);
        });
    }
}