package com.cinetick.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class WindowManager {
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel container;
    private static Stack<String> screenHistory = new Stack<>();

    public static void init(JFrame frame) {
        mainFrame = frame;
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        mainFrame.add(container);
    }

    public static void addScreen(JPanel panel, String name) {
        container.add(panel, name);
    }

    public static void showScreen(String name) {
        screenHistory.push(name);
        cardLayout.show(container, name);
    }

    public static void goBack() {
        if (screenHistory.size() > 1) {
            screenHistory.pop();
            cardLayout.show(container, screenHistory.peek());
        }
    }
}