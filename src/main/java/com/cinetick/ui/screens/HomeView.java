package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.components.*;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {
    public HomeView(MainDashboard dashboard) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        add(new HeroBanner(dashboard)); 
        add(new TabSelector(dashboard, "Now Showing"));

        JLabel title = new JLabel("Now Showing in Theaters", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        add(title);

        
        add(new MovieGrid(dashboard, 8)); 
        add(Box.createVerticalStrut(100)); 
    }
}