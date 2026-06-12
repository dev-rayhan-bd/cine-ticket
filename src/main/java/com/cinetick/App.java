package com.cinetick;

import com.cinetick.ui.MainDashboard;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import com.sun.jna.NativeLibrary; // Import this

public class App {
    public static void main(String[] args) {
      
        NativeLibrary.addSearchPath("libvlc", "C:\\Program Files\\VideoLAN\\VLC");
        System.setProperty("jna.library.path", "C:\\Program Files\\VideoLAN\\VLC");
// C:\Program Files\VideoLAN\VLC
        try { 
            UIManager.setLookAndFeel(new FlatDarkLaf()); 
            UIManager.put("Button.arc", 15);
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CineTick Pro");
            frame.setSize(1450, 950);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            com.cinetick.ui.WindowManager.init(frame);
            com.cinetick.ui.WindowManager.addScreen(new MainDashboard(), "DASHBOARD");
            com.cinetick.ui.WindowManager.showScreen("DASHBOARD");

            frame.setVisible(true);
        });
    }
}