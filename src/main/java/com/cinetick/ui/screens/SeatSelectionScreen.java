package com.cinetick.ui.screens;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class SeatSelectionScreen extends JPanel {
    public SeatSelectionScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BG_BLACK);

        // --- Left Section: Seat Selection ---
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        
        JButton backBtn = new JButton("← Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> WindowManager.goBack());
        leftPanel.add(backBtn, BorderLayout.NORTH);

        JPanel seatGrid = new JPanel(new GridLayout(10, 12, 8, 8));
        seatGrid.setOpaque(false);
        seatGrid.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        for (int i = 0; i < 120; i++) {
            JButton seat = new JButton();
            seat.setPreferredSize(new Dimension(30, 30));
            seat.setBackground(new Color(45, 45, 45));
            seat.addActionListener(e -> seat.setBackground(Theme.PRIMARY_RED));
            seatGrid.add(seat);
        }
        leftPanel.add(seatGrid, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.CENTER);

        // --- Right Section: Ticket Summary Sidebar (Ref Style) ---
        JPanel summarySidebar = new JPanel();
        summarySidebar.setPreferredSize(new Dimension(350, 900));
        summarySidebar.setBackground(new Color(25, 25, 25));
        summarySidebar.setLayout(new BoxLayout(summarySidebar, BoxLayout.Y_AXIS));
        summarySidebar.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

        JLabel sumTitle = new JLabel("Tickets Summary");
        sumTitle.setFont(Theme.TITLE_FONT);
        sumTitle.setForeground(Color.WHITE);

        JLabel movieInfo = new JLabel("<html><body style='width:250px;'><b>Movie:</b> Avengers Endgame<br><b>Hall:</b> Hall 1 (Premium)<br><b>Time:</b> 2:00 PM</body></html>");
        movieInfo.setForeground(Color.LIGHT_GRAY);

        JLabel total = new JLabel("Total: 500 BDT");
        total.setFont(new Font("Arial", Font.BOLD, 22));
        total.setForeground(Theme.PRIMARY_RED);

        JButton checkoutBtn = new JButton("PURCHASE TICKET");
        checkoutBtn.setMaximumSize(new Dimension(300, 60));
        checkoutBtn.setBackground(Theme.PRIMARY_RED);
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Proceeding to SSLCommerz..."));

        summarySidebar.add(sumTitle);
        summarySidebar.add(Box.createRigidArea(new Dimension(0, 30)));
        summarySidebar.add(movieInfo);
        summarySidebar.add(Box.createRigidArea(new Dimension(0, 40)));
        summarySidebar.add(total);
        summarySidebar.add(Box.createRigidArea(new Dimension(0, 40)));
        summarySidebar.add(checkoutBtn);

        add(summarySidebar, BorderLayout.EAST);
    }
}