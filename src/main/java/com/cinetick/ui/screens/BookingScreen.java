package com.cinetick.ui.screens;

import com.cinetick.ui.WindowManager;
import com.cinetick.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookingScreen extends JPanel {
    // --- State Management ---
    private int ticketCount = 1;
    private int seatPrice = 500;
    private String selectedSeatType = "Deluxe (500 BDT)";
    private final List<String> selectedSeats = new ArrayList<>();
    
    // UI Components that need updating
    private JLabel countLabel, totalPriceLabel, selectedSeatsLabel, typeLabel;
    private JPanel seatGrid;

    public BookingScreen() {
        setLayout(new BorderLayout());
        setBackground(Theme.BG_BLACK);

        // --- 1. LEFT CONTENT AREA (Scrollable Selection Flow) ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Back Button
        JButton backBtn = new JButton("← Back to Movie Details");
        backBtn.setForeground(Color.GRAY);
        backBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        backBtn.setContentAreaFilled(false);
        backBtn.setBorder(null);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        backBtn.addActionListener(e -> WindowManager.goBack());
        leftPanel.add(backBtn);
        leftPanel.add(Box.createVerticalStrut(25));

        // Section 1: Hall & Showtime
        leftPanel.add(createSectionHeader("1. Select Hall & Showtime"));
        JPanel hallPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        hallPanel.setOpaque(false);
        String[] showtimes = {"Hall 1 - 2:00 PM", "Hall 1 - 5:30 PM", "Hall 6 - 8:00 PM"};
        ButtonGroup timeGroup = new ButtonGroup();
        for (String time : showtimes) {
            JRadioButton rb = new JRadioButton(time);
            rb.setForeground(Color.WHITE);
            rb.setFont(new Font("Arial", Font.PLAIN, 14));
            timeGroup.add(rb);
            hallPanel.add(rb);
        }
        leftPanel.add(hallPanel);
        leftPanel.add(Box.createVerticalStrut(30));

        // Section 2: Category & Quantity
        leftPanel.add(createSectionHeader("2. Seat Category & Quantity"));
        JPanel typeQtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 10));
        typeQtyPanel.setOpaque(false);

        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Deluxe (500 BDT)", "Premium (700 BDT)"});
        typeCombo.setPreferredSize(new Dimension(220, 40));
        typeCombo.addActionListener(e -> {
            selectedSeatType = (String) typeCombo.getSelectedItem();
            seatPrice = selectedSeatType.contains("500") ? 500 : 700;
            updateSummary();
        });

        // Quantity Controls
        JPanel qtyCtrl = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        qtyCtrl.setOpaque(false);
        JButton minus = new JButton("-");
        countLabel = new JLabel("1");
        countLabel.setFont(new Font("Arial", Font.BOLD, 18));
        countLabel.setForeground(Color.WHITE);
        JButton plus = new JButton("+");
        
        plus.addActionListener(e -> { ticketCount++; countLabel.setText(String.valueOf(ticketCount)); updateSummary(); });
        minus.addActionListener(e -> { if(ticketCount > 1) { ticketCount--; countLabel.setText(String.valueOf(ticketCount)); updateSummary(); } });
        
        qtyCtrl.add(minus); qtyCtrl.add(countLabel); qtyCtrl.add(plus);
        
        JLabel tLbl = new JLabel("Category: "); tLbl.setForeground(Color.GRAY);
        JLabel qLbl = new JLabel("Quantity: "); qLbl.setForeground(Color.GRAY);
        
        typeQtyPanel.add(tLbl); typeQtyPanel.add(typeCombo);
        typeQtyPanel.add(Box.createHorizontalStrut(30));
        typeQtyPanel.add(qLbl); typeQtyPanel.add(qtyCtrl);
        leftPanel.add(typeQtyPanel);
        leftPanel.add(Box.createVerticalStrut(40));

        // Section 3: Seat Selection with Legend
        leftPanel.add(createSectionHeader("3. Select Your Seats"));
        leftPanel.add(Box.createVerticalStrut(15));

        // --- কালার ইন্ডিকেটর (Legend) ---
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        legendPanel.setOpaque(false);
        legendPanel.add(createLegendItem("Available", new Color(35, 35, 35)));
        legendPanel.add(createLegendItem("Selected", Theme.PRIMARY_RED));
        legendPanel.add(createLegendItem("Occupied", new Color(20, 20, 20)));
        leftPanel.add(legendPanel);
        leftPanel.add(Box.createVerticalStrut(20));

        // Visual Screen Indicator
        JLabel screenLine = new JLabel("SCREEN THIS WAY", SwingConstants.CENTER);
        screenLine.setOpaque(true);
        screenLine.setBackground(new Color(40, 40, 40));
        screenLine.setForeground(Color.GRAY);
        screenLine.setFont(new Font("Arial", Font.BOLD, 12));
        screenLine.setMaximumSize(new Dimension(800, 30));
        screenLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(screenLine);
        leftPanel.add(Box.createVerticalStrut(25));

        // Seat Grid (A-H, 1-12)
        seatGrid = new JPanel(new GridLayout(8, 12, 8, 8));
        seatGrid.setOpaque(false);
        seatGrid.setMaximumSize(new Dimension(850, 450));
        
        for (int r = 0; r < 8; r++) {
            char row = (char) ('A' + r);
            for (int col = 1; col <= 12; col++) {
                String seatNum = row + "" + col;
                JButton seat = new JButton(seatNum);
                seat.setFont(new Font("Arial", Font.PLAIN, 10));
                seat.setPreferredSize(new Dimension(50, 40));
                seat.setBackground(new Color(35, 35, 35));
                seat.setForeground(Color.DARK_GRAY);
                seat.setFocusPainted(false);
                
                // (Occupied)
                if ((r == 1 && col == 4) || (r == 4 && col == 8) || (r == 6 && col == 2)) {
                    seat.setEnabled(false);
                    seat.setBackground(new Color(20, 20, 20));
                    seat.setText("X");
                } else {
                    seat.addActionListener(e -> handleSeatSelection(seat, seatNum));
                }
                seatGrid.add(seat);
            }
        }
        leftPanel.add(seatGrid);

        // Entrance Indicator
        JLabel door = new JLabel("🚪 ENTRANCE DOOR", SwingConstants.CENTER);
        door.setForeground(new Color(0, 150, 255));
        door.setFont(new Font("Arial", Font.BOLD, 12));
        door.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(door);
        leftPanel.add(Box.createVerticalStrut(100)); // Bottom padding

        JScrollPane mainScroll = new JScrollPane(leftPanel);
        mainScroll.setBorder(null);
        mainScroll.setOpaque(false);
        mainScroll.getViewport().setOpaque(false);
        mainScroll.getVerticalScrollBar().setUnitIncrement(25);
        add(mainScroll, BorderLayout.CENTER);

        // --- 2. RIGHT SIDEBAR (Sticky Booking Summary) ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(400, 900));
        sidebar.setBackground(new Color(22, 22, 22));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(50, 40, 40, 40));

        JLabel sumTitle = new JLabel("Booking Summary");
        sumTitle.setFont(new Font("Arial", Font.BOLD, 28));
        sumTitle.setForeground(Color.WHITE);

        typeLabel = new JLabel("Category: Deluxe");
        typeLabel.setForeground(Color.GRAY);
        selectedSeatsLabel = new JLabel("Seats: None");
        selectedSeatsLabel.setForeground(Color.WHITE);
        selectedSeatsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        totalPriceLabel = new JLabel("Total: 0 BDT");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 30));
        totalPriceLabel.setForeground(Theme.PRIMARY_RED);

        JButton payBtn = new JButton("CONFIRM & PAY");
        payBtn.setMaximumSize(new Dimension(320, 65));
        payBtn.setBackground(Theme.PRIMARY_RED);
        payBtn.setForeground(Color.WHITE);
        payBtn.setFont(new Font("Arial", Font.BOLD, 18));
        payBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        payBtn.addActionListener(e -> {
            if (selectedSeats.size() == 0) {
                JOptionPane.showMessageDialog(this, "Please select at least one seat.");
            } else if (selectedSeats.size() != ticketCount) {
                JOptionPane.showMessageDialog(this, "You need to select exactly " + ticketCount + " seats.");
            } else {
                JOptionPane.showMessageDialog(this, "Redirecting to SSLCommerz API...");
            }
        });

        sidebar.add(sumTitle); sidebar.add(Box.createRigidArea(new Dimension(0, 45)));
        sidebar.add(createSummaryRow("Movie", "Rockstar (2D)"));
        sidebar.add(createSummaryRow("Hall", "Star Cineplex - Hall 1"));
        sidebar.add(typeLabel); sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(selectedSeatsLabel); sidebar.add(Box.createRigidArea(new Dimension(0, 50)));
        sidebar.add(totalPriceLabel); sidebar.add(Box.createRigidArea(new Dimension(0, 60)));
        sidebar.add(payBtn);

        add(sidebar, BorderLayout.EAST);
    }

    // --- Helper Methods ---

    private void handleSeatSelection(JButton btn, String num) {
        if (selectedSeats.contains(num)) {
            selectedSeats.remove(num);
            btn.setBackground(new Color(35, 35, 35));
            btn.setForeground(Color.DARK_GRAY);
        } else {
            if (selectedSeats.size() < ticketCount) {
                selectedSeats.add(num);
                btn.setBackground(Theme.PRIMARY_RED);
                btn.setForeground(Color.WHITE);
            } else {
                JOptionPane.showMessageDialog(this, "Maximum limit reached! You selected " + ticketCount + " tickets.");
            }
        }
        updateSummary();
    }

    private void updateSummary() {
        typeLabel.setText("Category: " + selectedSeatType);
        selectedSeatsLabel.setText("Seats: " + (selectedSeats.isEmpty() ? "None" : String.join(", ", selectedSeats)));
        totalPriceLabel.setText("Total: " + (selectedSeats.size() * seatPrice) + " BDT");
    }

    private JPanel createLegendItem(String label, Color color) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        p.setOpaque(false);
        JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(18, 18));
        box.setBackground(color);
        box.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel l = new JLabel(label);
        l.setForeground(Color.LIGHT_GRAY);
        l.setFont(new Font("Arial", Font.PLAIN, 13));
        p.add(box); p.add(l);
        return p;
    }

    private JLabel createSectionHeader(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Arial", Font.BOLD, 22));
        l.setForeground(Color.WHITE);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel createSummaryRow(String key, String val) {
        JLabel l = new JLabel("<html><font color='#888888'>" + key + ":</font> &nbsp; <font color='white'>" + val + "</font></html>");
        l.setFont(new Font("Arial", Font.PLAIN, 16));
        l.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        return l;
    }
}