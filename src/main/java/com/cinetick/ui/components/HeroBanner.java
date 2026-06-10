package com.cinetick.ui.components;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeroBanner extends JPanel {
    private JLabel bgLabel;
    private JLabel titleLabel;
    private JLabel descLabel;
    private int currentSlide = 0;
    private MainDashboard dashboard;

    private String[][] slides = {
        {"AVENGERS: ENDGAME", "After the devastating events of Infinity War, the universe is in ruins.", "https://wallpaperaccess.com/full/104598.jpg"},
        {"INTERSTELLAR", "A team of explorers travel through a wormhole in space to ensure humanity's survival.", "https://wallpapercave.com/wp/wp1811195.jpg"},
        {"THE BATMAN", "Unmask the truth of Gotham City.", "https://images.hdqwalls.com/download/the-batman-2022-4k-5b-2560x1440.jpg"}
    };

    public HeroBanner(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setPreferredSize(new Dimension(1100, 500));
        setMaximumSize(new Dimension(2560, 500));
        setLayout(new BorderLayout());
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        bgLabel = new JLabel();
        bgLabel.setLayout(new BorderLayout());
        
   
        JPanel overlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gp = new GradientPaint(0, 0, new Color(0,0,0,100), 0, getHeight(), new Color(0,0,0,255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        overlay.setLayout(new BoxLayout(overlay, BoxLayout.Y_AXIS));
        overlay.setOpaque(false);
        overlay.setBorder(BorderFactory.createEmptyBorder(120, 60, 0, 0));

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 55));
        titleLabel.setForeground(Color.WHITE);

        descLabel = new JLabel();
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        descLabel.setForeground(Theme.TEXT_GRAY);

        JButton playBtn = new JButton("▶ WATCH NOW");
        playBtn.setBackground(Theme.PRIMARY_RED);
        playBtn.setForeground(Color.WHITE);
        playBtn.setPreferredSize(new Dimension(180, 50));
        playBtn.setFocusPainted(false);

        overlay.add(titleLabel);
        overlay.add(Box.createRigidArea(new Dimension(0, 15)));
        overlay.add(descLabel);
        overlay.add(Box.createRigidArea(new Dimension(0, 30)));
        overlay.add(playBtn);

        bgLabel.add(overlay, BorderLayout.CENTER);
        add(bgLabel);

   //details screen navigation logic
        overlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dashboard.openMovieDetails(slides[currentSlide][0], slides[currentSlide][2]);
            }
        });

   
        updateSlide();
        Timer timer = new Timer(6000, e -> {
            currentSlide = (currentSlide + 1) % slides.length;
            updateSlide();
        });
        timer.start();
    }

    private void updateSlide() {
        titleLabel.setText(slides[currentSlide][0]);
        descLabel.setText("<html><body style='width: 550px;'>" + slides[currentSlide][1] + "</body></html>");
        
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(slides[currentSlide][2], 1600, 500);
            SwingUtilities.invokeLater(() -> bgLabel.setIcon(icon));
        }).start();
    }
}