package com.cinetick.ui.screens;

import com.cinetick.config.AppConfig;
import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.components.MovieGrid;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class TVShowsView extends JPanel {
    public TVShowsView(MainDashboard dashboard) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel title = new JLabel("Popular TV Series", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Color.CYAN);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        
        add(title);
   add(new MovieGrid(dashboard, "TV_SHOWS", AppConfig.LIMIT_TV_SHOWS));
        add(Box.createVerticalStrut(100));
    }
}