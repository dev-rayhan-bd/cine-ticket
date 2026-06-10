package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.components.TabSelector;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class ShowTimesView extends JPanel {
    private MainDashboard dashboard;

    public ShowTimesView(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        // ট্যাব বার যোগ করা হলো
        add(new TabSelector(dashboard, "Show Times"));

        JLabel title = new JLabel("Theater Schedule", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.PRIMARY_RED);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        add(title);

        String[][] schedule = {
            {"Star Cineplex - Bashundhara City", "Rockstar", "11:00 AM", "2:30 PM", "7:00 PM"},
            {"Star Cineplex - SKS Tower", "The Batman", "1:00 PM", "4:30 PM", "8:15 PM"},
            {"Star Cineplex - Sony Square", "Joker", "10:30 AM", "3:00 PM", "9:00 PM"}
        };

        for (String[] hall : schedule) {
            add(createHallCard(hall));
            add(Box.createVerticalStrut(20));
        }
    }

    private JPanel createHallCard(String[] data) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(30, 30, 30));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        card.setMaximumSize(new Dimension(1200, 120));

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setOpaque(false);
        JLabel name = new JLabel(data[0]);
        name.setFont(new Font("Arial", Font.BOLD, 18));
        name.setForeground(Color.WHITE);
        JLabel movie = new JLabel("Playing: " + data[1]);
        movie.setForeground(Color.GRAY);
        info.add(name); info.add(movie);

        JPanel times = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        times.setOpaque(false);
        for (int i = 2; i < data.length; i++) {
            JButton tBtn = new JButton(data[i]);
            tBtn.setBackground(new Color(45, 10, 80));
            tBtn.setForeground(Color.WHITE);
            tBtn.addActionListener(e -> dashboard.navigateTo("BUY_TICKETS"));
            times.add(tBtn);
        }

        card.add(info, BorderLayout.WEST);
        card.add(times, BorderLayout.EAST);
        return card;
    }
}