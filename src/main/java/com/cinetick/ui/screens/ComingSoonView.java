package com.cinetick.ui.screens;

import com.cinetick.config.AppConfig;
import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.components.MovieGrid;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class ComingSoonView extends JPanel {
    public ComingSoonView(MainDashboard dashboard) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel title = new JLabel("Arriving Soon in Theaters", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(new Color(0, 255, 150)); // Fresh Greenish Color
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        
        add(title);
     add(new MovieGrid(dashboard, "COMING_SOON", AppConfig.LIMIT_COMING_SOON));
        add(Box.createVerticalStrut(100));
    }
}