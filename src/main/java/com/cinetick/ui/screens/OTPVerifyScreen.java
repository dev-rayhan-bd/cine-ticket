package com.cinetick.ui.screens;

import com.cinetick.dao.UserDAO;
import com.cinetick.ui.WindowManager;
import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import com.cinetick.service.AuthSession;
import com.cinetick.models.User;
import javax.swing.*;
import java.awt.*;

public class OTPVerifyScreen extends JPanel {
    public OTPVerifyScreen() {
        setBackground(Theme.BG_BLACK);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(450, 500));
        card.setBackground(Theme.NAVBAR_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        JLabel icon = new JLabel("🔒");
        icon.setFont(new Font("Arial", Font.PLAIN, 50));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Verify Identity");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("<html><center>We've sent a 6-digit code to your email.<br>Please enter it below to continue.</center></html>");
        sub.setForeground(Color.GRAY);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Variable Name: otpInput ---
        JTextField otpInput = new JTextField();
        otpInput.setMaximumSize(new Dimension(300, 60));
        otpInput.setHorizontalAlignment(JTextField.CENTER);
        otpInput.setFont(new Font("Monospaced", Font.BOLD, 35));
        otpInput.setBackground(new Color(30, 30, 30));
        otpInput.setForeground(Theme.PRIMARY_RED);
        otpInput.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        // --- Variable Name: verifyBtn ---
        JButton verifyBtn = new JButton("VERIFY CODE");
        verifyBtn.setMaximumSize(new Dimension(350, 55));
        verifyBtn.setBackground(Theme.PRIMARY_RED);
        verifyBtn.setForeground(Color.WHITE);
        verifyBtn.setFont(new Font("Arial", Font.BOLD, 14));
        verifyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        verifyBtn.addActionListener(e -> {
            String email = SignupScreen.lastRegisteredEmail;
            String code = otpInput.getText().trim();

            if (UserDAO.verifyOTP(email, code)) {
              
                User user = UserDAO.getUserByEmail(email);
                
                if (user != null) {
                 
                    AuthSession.login(user);
                    
                  
                    MainDashboard dashboard = (MainDashboard) WindowManager.getScreen("DASHBOARD");
                    if (dashboard != null) {
                        dashboard.refreshNavbar(); 
                    }

                    JOptionPane.showMessageDialog(this, "Verification Success! Welcome " + user.name);
                    WindowManager.showScreen("DASHBOARD");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid OTP! Please try again.");
            }
        });

        card.add(icon); card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(title); card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(sub); card.add(Box.createRigidArea(new Dimension(0, 50)));
        card.add(otpInput); card.add(Box.createRigidArea(new Dimension(0, 40)));
        card.add(verifyBtn);

        add(card);
    }
}