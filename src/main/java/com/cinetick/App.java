package com.cinetick;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.screens.*;
import com.cinetick.ui.MainDashboard;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Industry Standard Theme Setup
        try { 
            UIManager.setLookAndFeel(new FlatDarkLaf()); 
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
        } catch (Exception e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CineTick Pro");
            frame.setSize(1450, 950);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Initialize Unified Navigation
            WindowManager.init(frame);

            // Register ALL Production Screens
            WindowManager.addScreen(new LoginScreen(), "LOGIN");
            WindowManager.addScreen(new SignupScreen(), "SIGNUP");
            WindowManager.addScreen(new MainDashboard(), "DASHBOARD");
            WindowManager.addScreen(new ForgotPasswordScreen(), "FORGOT_PASSWORD");
            WindowManager.addScreen(new OTPVerifyScreen(), "OTP_VERIFY");
            // WindowManager.addScreen(new MovieDetailsScreen("Rockstar"), "DETAILS");
            // WindowManager.addScreen(new BookingScreen(), "SEAT_SELECTION");
            // WindowManager.addScreen(new ProfileScreen(), "PROFILE");

            // Start Flow
            WindowManager.showScreen("LOGIN");
            frame.setVisible(true);
        });
    }
}