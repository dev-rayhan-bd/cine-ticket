package com.cinetick.ui.components;

import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class Sidebar extends JPanel {
    private final String[] menuItems = {"Home", "TV Shows", "Movies", "Trending", "Profile"};
    private final JButton[] buttons = new JButton[menuItems.length];

    public Sidebar(Consumer<String> onNavigate) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.SIDEBAR_BG);
        setPreferredSize(new Dimension(240, 900));
        setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        for (int i = 0; i < menuItems.length; i++) {
            String item = menuItems[i];
            buttons[i] = new JButton("  " + item);
            buttons[i].setForeground(Theme.TEXT_GRAY);
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 17));
            buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
            buttons[i].setMaximumSize(new Dimension(240, 50));
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

            buttons[i].addActionListener(e -> {
                setActiveItem(item);
                onNavigate.accept(item);
            });
            add(buttons[i]);
            add(Box.createRigidArea(new Dimension(0, 15)));
        }
        setActiveItem("Home");
    }

public void setActiveItem(String activeItem) {
    for (JButton btn : buttons) {
        String btnText = btn.getText().trim();
        if (btnText.equals(activeItem)) {
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            // বাম পাশে একটি লাল দাগ (Indication)
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, Theme.PRIMARY_RED),
                BorderFactory.createEmptyBorder(0, 20, 0, 0)
            ));
        } else {
            btn.setForeground(Theme.TEXT_GRAY);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 17));
            btn.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        }
    }
}
}