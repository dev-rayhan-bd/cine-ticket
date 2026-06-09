package com.cinetick.ui.screens;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JPanel {
    public LoginScreen() {
        setBackground(Theme.BG_BLACK);
        setLayout(new GridBagLayout());

        JPanel loginCard = new JPanel();
        loginCard.setPreferredSize(new Dimension(400, 550));
        loginCard.setBackground(Theme.NAVBAR_BG);
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
        loginCard.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        JLabel title = new JLabel("Sign In");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(320, 50));
        emailField.setBorder(BorderFactory.createTitledBorder(null, "Email", 0, 0, null, Color.GRAY));

        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(320, 50));
        passField.setBorder(BorderFactory.createTitledBorder(null, "Password", 0, 0, null, Color.GRAY));

        JButton loginBtn = new JButton("Sign In");
        loginBtn.setMaximumSize(new Dimension(320, 50));
        loginBtn.setBackground(Theme.PRIMARY_RED);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.addActionListener(e -> WindowManager.showScreen("DASHBOARD"));

        JLabel forgotPass = new JLabel("Forgot password?");
        forgotPass.setForeground(Color.GRAY);
        forgotPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPass.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { WindowManager.showScreen("FORGOT_PASSWORD"); }
        });

        JLabel signupLink = new JLabel("New to CineTick? Sign up now.");
        signupLink.setForeground(Color.WHITE);
        signupLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { WindowManager.showScreen("SIGNUP"); }
        });

        loginCard.add(title); loginCard.add(Box.createRigidArea(new Dimension(0, 40)));
        loginCard.add(emailField); loginCard.add(Box.createRigidArea(new Dimension(0, 20)));
        loginCard.add(passField); loginCard.add(Box.createRigidArea(new Dimension(0, 40)));
        loginCard.add(loginBtn); loginCard.add(Box.createRigidArea(new Dimension(0, 15)));
        loginCard.add(forgotPass); loginCard.add(Box.createRigidArea(new Dimension(0, 30)));
        loginCard.add(signupLink);

        add(loginCard);
    }
}