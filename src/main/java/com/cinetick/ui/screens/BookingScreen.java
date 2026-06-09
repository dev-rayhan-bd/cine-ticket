package com.cinetick.ui.screens;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class BookingScreen extends JPanel {
    private int ticketCount = 1;
    private JLabel countLabel;

    public BookingScreen() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Cineplex Style Light Theme

        // --- Left Section: Booking Details ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // 1. Hall & Showtime Selection
        leftPanel.add(createSectionTitle("Select Show Time"));
        JPanel halls = new JPanel(new GridLayout(2, 1, 0, 15));
        halls.setOpaque(false);
        halls.add(createHallRow("Hall 1", "2:00 PM"));
        halls.add(createHallRow("Hall 6", "4:30 PM", "7:30 PM"));
        leftPanel.add(halls);

        // 2. Ticket Quantity & Type
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(createSectionTitle("Ticket Quantity"));
        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        qtyPanel.setOpaque(false);
        
        JButton minus = new JButton("-");
        countLabel = new JLabel("1 Tickets");
        JButton plus = new JButton("+");
        
        plus.addActionListener(e -> { ticketCount++; countLabel.setText(ticketCount + " Tickets"); });
        minus.addActionListener(e -> { if(ticketCount > 1) { ticketCount--; countLabel.setText(ticketCount + " Tickets"); } });
        
        qtyPanel.add(minus); qtyPanel.add(countLabel); qtyPanel.add(plus);
        leftPanel.add(qtyPanel);

        // 3. Exact Seat Grid (Ref Style)
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(createSectionTitle("Select Seats (Screen this way)"));
        JPanel seatMap = new JPanel(new GridLayout(8, 15, 5, 5));
        seatMap.setBackground(new Color(245, 245, 250));
        for (int row = 0; row < 8; row++) {
            char rowChar = (char)('A' + row);
            for (int col = 1; col <= 15; col++) {
                JButton seat = new JButton(rowChar + "" + col);
                seat.setFont(new Font("Arial", Font.PLAIN, 9));
                seat.setPreferredSize(new Dimension(40, 30));
                seat.setBackground(Color.LIGHT_GRAY);
                seat.addActionListener(e -> seat.setBackground(new Color(0, 150, 0))); // Green on select
                seatMap.add(seat);
            }
        }
        leftPanel.add(new JScrollPane(seatMap));

        add(leftPanel, BorderLayout.CENTER);

        // --- Right Section: Summary Sidebar ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(350, 900));
        sidebar.setBackground(new Color(250, 245, 255));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

        JLabel sumTitle = new JLabel("Tickets Summary");
        sumTitle.setFont(new Font("Arial", Font.BOLD, 22));
        
        JLabel details = new JLabel("<html><b>Movie:</b> Rockstar<br><b>Hall:</b> Hall 1<br><b>Time:</b> 2:00 PM<br><b>Seat Type:</b> Premium</html>");
        
        JButton purchaseBtn = new JButton("PURCHASE TICKET");
        purchaseBtn.setBackground(new Color(90, 30, 150));
        purchaseBtn.setForeground(Color.WHITE);
        purchaseBtn.setPreferredSize(new Dimension(280, 50));
        purchaseBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Redirecting to SSLCommerz..."));

        sidebar.add(sumTitle); sidebar.add(Box.createRigidArea(new Dimension(0, 30)));
        sidebar.add(details); sidebar.add(Box.createVerticalGlue());
        sidebar.add(purchaseBtn);
        
        add(sidebar, BorderLayout.EAST);
    }

    private JLabel createSectionTitle(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Arial", Font.BOLD, 18));
        l.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        return l;
    }

    private JPanel createHallRow(String name, String... times) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        p.add(new JLabel("  " + name), BorderLayout.WEST);
        JPanel tPanel = new JPanel();
        for(String t : times) {
            JButton b = new JButton(t);
            b.setForeground(new Color(90, 30, 150));
            tPanel.add(b);
        }
        p.add(tPanel, BorderLayout.EAST);
        return p;
    }
}