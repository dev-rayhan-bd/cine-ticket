package com.cinetick.ui.components;

import javax.swing.*;
import java.awt.*;

public class GenreChip extends JButton {
    public GenreChip(String text) {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(35, 35, 35));
        setForeground(Color.LIGHT_GRAY);
        setFont(new Font("Arial", Font.BOLD, 13));
    
        putClientProperty("JButton.buttonType", "roundRect");
    }
}