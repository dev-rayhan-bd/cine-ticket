package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VideoPlayerScreen extends JPanel {
    private EmbeddedMediaPlayerComponent mediaPlayer;
    private MainDashboard dashboard;
    private JSlider seekSlider, volSlider;
    private JLabel timeLabel;
    private JButton playPauseBtn, fsBtn;
    private boolean isDragging = false;
    private boolean isFullScreen = false;

    public VideoPlayerScreen(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

       
        mediaPlayer = new EmbeddedMediaPlayerComponent();
        add(mediaPlayer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(15, 15, 15));
        
        
        seekSlider = new JSlider(0, 1000, 0);
        seekSlider.setOpaque(false);
        seekSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        seekSlider.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { isDragging = true; }
            public void mouseReleased(MouseEvent e) {
                float pos = seekSlider.getValue() / 1000.0f;
                mediaPlayer.mediaPlayer().controls().setPosition(pos);
                isDragging = false;
            }
        });
        bottomPanel.add(seekSlider);

        JPanel controls = new JPanel(new BorderLayout(15, 0));
        controls.setOpaque(false);
        controls.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20));

      
        JPanel leftGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        leftGroup.setOpaque(false);

        playPauseBtn = new JButton("PAUSE"); 
        playPauseBtn.setFont(new Font("Arial", Font.BOLD, 12));
        playPauseBtn.setPreferredSize(new Dimension(100, 35));
        playPauseBtn.addActionListener(e -> {
            mediaPlayer.mediaPlayer().controls().pause();
            playPauseBtn.setText(mediaPlayer.mediaPlayer().status().isPlaying() ? "PAUSE" : "PLAY");
        });

        timeLabel = new JLabel("00:00 / 00:00");
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 14));

        leftGroup.add(playPauseBtn);
        leftGroup.add(timeLabel);
        controls.add(leftGroup, BorderLayout.WEST);

        JPanel rightGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightGroup.setOpaque(false);

        volSlider = new JSlider(0, 100, 80);
        volSlider.setPreferredSize(new Dimension(120, 35));
        volSlider.setOpaque(false);
        volSlider.addChangeListener(e -> mediaPlayer.mediaPlayer().audio().setVolume(volSlider.getValue()));

        fsBtn = new JButton("FULLSCREEN [⛶]");
        fsBtn.addActionListener(e -> toggleFullScreen());

        rightGroup.add(new JLabel("<html><b style='color:white;'>VOL:</b></html>"));
        rightGroup.add(volSlider);
        rightGroup.add(fsBtn);
        controls.add(rightGroup, BorderLayout.EAST);

        bottomPanel.add(controls);
        add(bottomPanel, BorderLayout.SOUTH);

        JButton closeBtn = new JButton("✕ CLOSE TRAILER");
        closeBtn.setBackground(Theme.PRIMARY_RED);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.addActionListener(e -> {
            mediaPlayer.mediaPlayer().controls().stop();
            if (isFullScreen) toggleFullScreen();
            dashboard.navigateTo("HOME");
        });
        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.setBackground(Color.BLACK);
        top.add(closeBtn);
        add(top, BorderLayout.NORTH);

      
        new Timer(1000, e -> refreshStatus()).start();
    }

    private void toggleFullScreen() {
        isFullScreen = !isFullScreen;
        fsBtn.setText(isFullScreen ? "EXIT FULLSCREEN" : "FULLSCREEN [⛶]");
        dashboard.toggleLayoutForPlayer(isFullScreen); 
    }

    private void refreshStatus() {
        if (!mediaPlayer.mediaPlayer().status().isPlaying() || isDragging) return;
        
        float pos = mediaPlayer.mediaPlayer().status().position();
        seekSlider.setValue((int) (pos * 1000));
        
        long cur = mediaPlayer.mediaPlayer().status().time() / 1000;
        long tot = mediaPlayer.mediaPlayer().status().length() / 1000;
        timeLabel.setText(String.format("%02d:%02d / %02d:%02d", cur/60, cur%60, tot/60, tot%60));
    }

    public void playVideo(String url) {
        mediaPlayer.mediaPlayer().media().play(url);
    }
}