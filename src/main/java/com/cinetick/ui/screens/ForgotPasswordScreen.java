package com.cinetick.ui.screens;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class ForgotPasswordScreen extends JPanel {
    public ForgotPasswordScreen() {
        setBackground(Theme.BG_BLACK);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(450, 450));
        card.setBackground(Theme.NAVBAR_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        JLabel title = new JLabel("Reset Password");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitle = new JLabel("<html><center>Enter your email and we'll send you an OTP to reset your password.</center></html>");
        subTitle.setForeground(Color.GRAY);
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subTitle.setMaximumSize(new Dimension(350, 60));

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(350, 50));
        emailField.setBackground(new Color(40, 40, 40));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createTitledBorder(null, "Email Address", 0, 0, null, Color.GRAY));

        JButton sendBtn = new JButton("SEND OTP");
        sendBtn.setMaximumSize(new Dimension(350, 50));
        sendBtn.setBackground(Theme.PRIMARY_RED);
        sendBtn.setForeground(Color.WHITE);
        sendBtn.setFont(new Font("Arial", Font.BOLD, 14));
        sendBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendBtn.addActionListener(e -> WindowManager.showScreen("OTP_VERIFY"));

        JButton backBtn = new JButton("← Back to Login");
        backBtn.setForeground(Color.LIGHT_GRAY);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> WindowManager.goBack());

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(subTitle);
        card.add(Box.createRigidArea(new Dimension(0, 40)));
        card.add(emailField);
        card.add(Box.createRigidArea(new Dimension(0, 30)));
        card.add(sendBtn);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(backBtn);

        add(card);
    }
}