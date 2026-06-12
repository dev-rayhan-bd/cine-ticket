package com.cinetick.ui.components;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import com.cinetick.ui.utils.ImageUtil;
import com.cinetick.service.TMDBService;
import com.cinetick.models.Movie;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class HeroBanner extends JPanel {
    private JLabel bgLabel;
    private JLabel titleLabel, descLabel;
    private List<Movie> trendingMovies;
    private int currentSlide = 0;
    private MainDashboard dashboard;

    public HeroBanner(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setPreferredSize(new Dimension(1100, 500));
        setLayout(new BorderLayout());
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        bgLabel = new JLabel();
        bgLabel.setLayout(new BorderLayout());
        
        JPanel overlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, new Color(0,0,0,80), 0, getHeight(), new Color(0,0,0,255)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        overlay.setLayout(new BoxLayout(overlay, BoxLayout.Y_AXIS));
        overlay.setOpaque(false);
        overlay.setBorder(BorderFactory.createEmptyBorder(150, 60, 0, 0));

        titleLabel = new JLabel("Loading...");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 55));
        titleLabel.setForeground(Color.WHITE);

        descLabel = new JLabel();
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        descLabel.setForeground(Color.LIGHT_GRAY);

        overlay.add(titleLabel);
        overlay.add(Box.createRigidArea(new Dimension(0, 10)));
        overlay.add(descLabel);
        bgLabel.add(overlay, BorderLayout.CENTER);
        add(bgLabel);

        // Click to Details Logic
        overlay.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(trendingMovies != null) {
                    Movie m = trendingMovies.get(currentSlide);
                    dashboard.openMovieDetails(m.id, m.title, m.rating, m.imgUrl, m.overview);
                }
            }
        });

        new Thread(() -> {
            trendingMovies = TMDBService.getMovies("TRENDING", 5);
            if (!trendingMovies.isEmpty()) startSlider();
        }).start();
    }

    private void startSlider() {
        updateSlide();
        new Timer(6000, e -> {
            currentSlide = (currentSlide + 1) % trendingMovies.size();
            updateSlide();
        }).start();
    }

    private void updateSlide() {
        Movie m = trendingMovies.get(currentSlide);
        titleLabel.setText(m.title.toUpperCase());
        descLabel.setText("<html><body style='width:550px;'>" + m.overview + "</body></html>");
        new Thread(() -> {
            ImageIcon icon = ImageUtil.loadAndScale(m.backdropUrl, 1600, 500);
            SwingUtilities.invokeLater(() -> bgLabel.setIcon(icon));
        }).start();
    }
}