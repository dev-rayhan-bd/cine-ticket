package com.cinetick.ui.components;

import com.cinetick.ui.MainDashboard;
import javax.swing.*;
import java.awt.*;

public class MovieGrid extends JPanel {
    public MovieGrid(MainDashboard dashboard, int count) {
        setOpaque(false);
        setLayout(new GridLayout(0, 5, 25, 35));
        setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));

        String img = "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg";
        
        for (int i = 0; i < count; i++) {
          
            add(new MovieCard(dashboard, "Movie Title " + (i+1), "9.0", img));
        }
    }
}