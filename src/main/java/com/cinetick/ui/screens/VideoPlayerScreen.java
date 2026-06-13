package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;
import java.awt.*;

public class VideoPlayerScreen extends JPanel {
    private EmbeddedMediaPlayerComponent mediaPlayer;
    private MainDashboard dashboard;
    private JSlider seekSlider;
    private JLabel timeLabel;
    private boolean isDragging = false;

    public VideoPlayerScreen(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // ১. VLCJ Player Initialization
        mediaPlayer = new EmbeddedMediaPlayerComponent();
        add(mediaPlayer, BorderLayout.CENTER);

        // ২. Top Bar (Close Button)
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setOpaque(false);
        JButton closeBtn = new JButton("✕ CLOSE TRAILER");
        closeBtn.setBackground(Theme.PRIMARY_RED);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.addActionListener(e -> {
            mediaPlayer.mediaPlayer().controls().stop();
            dashboard.navigateTo("HOME");
        });
        topBar.add(closeBtn);
        add(topBar, BorderLayout.NORTH);

        // ৩. Bottom Control Bar
        add(createControlPanel(), BorderLayout.SOUTH);

        // ৪. UI আপডেট করার টাইমার (প্রতি সেকেন্ডে একবার চলবে)
        Timer timer = new Timer(1000, e -> refreshPlayerStatus()); // নাম পরিবর্তন করা হয়েছে
        timer.start();
    }

    private JPanel createControlPanel() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBackground(new Color(20, 20, 20));
        p.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Seek Bar
        seekSlider = new JSlider(0, 1000, 0);
        seekSlider.setOpaque(false);
        seekSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) { isDragging = true; }
            public void mouseReleased(java.awt.event.MouseEvent e) {
                float pos = seekSlider.getValue() / 1000.0f;
                mediaPlayer.mediaPlayer().controls().setPosition(pos);
                isDragging = false;
            }
        });
        p.add(seekSlider, BorderLayout.NORTH);

        // Left: Play/Pause & Time
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        left.setOpaque(false);
        JButton playBtn = new JButton("⏯");
        playBtn.addActionListener(e -> mediaPlayer.mediaPlayer().controls().pause());
        timeLabel = new JLabel("00:00 / 00:00");
        timeLabel.setForeground(Color.WHITE);
        left.add(playBtn); left.add(timeLabel);
        p.add(left, BorderLayout.WEST);

        // Right: Volume
        JSlider volSlider = new JSlider(0, 100, 80);
        volSlider.setPreferredSize(new Dimension(100, 20));
        volSlider.setOpaque(false);
        volSlider.addChangeListener(e -> mediaPlayer.mediaPlayer().audio().setVolume(volSlider.getValue()));
        p.add(volSlider, BorderLayout.EAST);

        return p;
    }

    private void refreshPlayerStatus() { // মেথড এর নাম ফিক্স করা হয়েছে
        if (!mediaPlayer.mediaPlayer().status().isPlaying() || isDragging) return;
        
        float pos = mediaPlayer.mediaPlayer().status().position();
        seekSlider.setValue((int) (pos * 1000));

        long current = mediaPlayer.mediaPlayer().status().time() / 1000;
        long total = mediaPlayer.mediaPlayer().status().length() / 1000;
        timeLabel.setText(formatTime(current) + " / " + formatTime(total));
    }

    private String formatTime(long s) {
        if (s < 0) return "00:00";
        return String.format("%02d:%02d", s / 60, s % 60);
    }

    public void playVideo(String url) {
        System.out.println("🎥 Streaming: " + url);
        mediaPlayer.mediaPlayer().media().play(url);
    }
}