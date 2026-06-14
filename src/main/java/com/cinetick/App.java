package com.cinetick;
import com.cinetick.ui.WindowManager;
import com.cinetick.ui.screens.*;
import com.cinetick.ui.MainDashboard;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class App {
    static {
        System.setProperty("jna.library.path", "C:\\Program Files\\VideoLAN\\VLC");
    }
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(new FlatDarkLaf()); } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CineTick Pro");
            frame.setSize(1450, 950);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            WindowManager.init(frame);
            WindowManager.addScreen(new LoginScreen(), "LOGIN");
            WindowManager.addScreen(new SignupScreen(), "SIGNUP");
            WindowManager.addScreen(new OTPVerifyScreen(), "OTP_VERIFY");
            WindowManager.addScreen(new ForgotPasswordScreen(), "FORGOT_PASSWORD");
            WindowManager.addScreen(new MainDashboard(), "DASHBOARD");
            WindowManager.showScreen("LOGIN");
            frame.setVisible(true);
        });
    }
}