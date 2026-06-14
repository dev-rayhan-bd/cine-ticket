package com.cinetick.ui.screens;

import com.cinetick.dao.UserDAO;
import com.cinetick.models.User;
import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SignupScreen extends JPanel {
    private JLabel imgPreview;
    private File selectedImage;
    public static String lastRegisteredEmail = ""; 

    public SignupScreen() {
        setBackground(Theme.BG_BLACK);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(500, 750));
        card.setBackground(Theme.NAVBAR_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Profile Image Section ---
        imgPreview = new JLabel("No Image", SwingConstants.CENTER);
        imgPreview.setPreferredSize(new Dimension(120, 120));
        imgPreview.setMaximumSize(new Dimension(120, 120));
        imgPreview.setBorder(BorderFactory.createLineBorder(Theme.PRIMARY_RED, 2));
        imgPreview.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton uploadBtn = new JButton("Upload Photo");
        uploadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedImage = chooser.getSelectedFile();
                imgPreview.setText("");
                imgPreview.setIcon(ImageUtil.fromFile(selectedImage, 120, 120));
            }
        });

        JTextField name = createField("Full Name");
        JTextField email = createField("Email");
        JPasswordField pass = new JPasswordField();
        pass.setMaximumSize(new Dimension(400, 50));
        pass.setBorder(BorderFactory.createTitledBorder(null, "Password", 0, 0, null, Color.GRAY));

        JButton signupBtn = new JButton("SIGN UP");
        signupBtn.setBackground(Theme.PRIMARY_RED);
        signupBtn.setForeground(Color.WHITE);
        signupBtn.setMaximumSize(new Dimension(400, 55));
        
        // --- LOGIC START ---
        signupBtn.addActionListener(e -> {
            String emailTxt = email.getText();
            String nameTxt = name.getText();
            String passTxt = new String(pass.getPassword());
            String picPath = (selectedImage != null) ? selectedImage.getAbsolutePath() : "";

            if (emailTxt.isEmpty() || passTxt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
                return;
            }

            lastRegisteredEmail = emailTxt; // ওটিপি স্ক্রিনের জন্য ইমেইল সেভ করা হলো
            User u = new User(nameTxt, emailTxt, passTxt, picPath);
            
            if (UserDAO.register(u)) {
                JOptionPane.showMessageDialog(this, "OTP Sent! Please check your VS Code Terminal.");
                WindowManager.showScreen("OTP_VERIFY");
            } else {
                JOptionPane.showMessageDialog(this, "Email already exists or DB Error!");
            }
        });

        card.add(title); card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(imgPreview); card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(uploadBtn); card.add(Box.createRigidArea(new Dimension(0, 30)));
        card.add(name); card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(email); card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(pass); card.add(Box.createRigidArea(new Dimension(0, 40)));
        card.add(signupBtn);

        add(card);
    }

    private JTextField createField(String hint) {
        JTextField f = new JTextField();
        f.setMaximumSize(new Dimension(400, 50));
        f.setBackground(new Color(45,45,45));
        f.setForeground(Color.WHITE);
        f.setBorder(BorderFactory.createTitledBorder(null, hint, 0, 0, null, Color.GRAY));
        return f;
    }
}