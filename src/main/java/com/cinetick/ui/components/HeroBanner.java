package com.cinetick.ui.components;

import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class HeroBanner extends JPanel {
    public HeroBanner() {
        setPreferredSize(new Dimension(1100, 450));
        setMaximumSize(new Dimension(2000, 450));
        setBackground(new Color(25, 25, 25));
        setLayout(new BorderLayout());

        JPanel overlay = new JPanel();
        overlay.setLayout(new BoxLayout(overlay, BoxLayout.Y_AXIS));
        overlay.setOpaque(false);
        overlay.setBorder(BorderFactory.createEmptyBorder(80, 60, 0, 0));

        JLabel title = new JLabel("AVENGERS: ENDGAME");
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        title.setForeground(Color.WHITE);

        JLabel desc = new JLabel("<html><body style='width: 450px;'>After the devastating events of Infinity War, the universe is in ruins. The Avengers assemble once more in order to restore balance to the universe.</body></html>");
        desc.setFont(new Font("SansSerif", Font.PLAIN, 16));
        desc.setForeground(Theme.TEXT_GRAY);

        JButton playBtn = new JButton("▶ Play Trailer");
        playBtn.setBackground(Theme.PRIMARY_RED);
        playBtn.setForeground(Color.WHITE);
        playBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        playBtn.setPreferredSize(new Dimension(160, 45));

        overlay.add(title);
        overlay.add(Box.createRigidArea(new Dimension(0, 10)));
        overlay.add(desc);
        overlay.add(Box.createRigidArea(new Dimension(0, 20)));
        overlay.add(playBtn);

        add(overlay, BorderLayout.WEST);
    }
}