package com.cinetick;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class App {
    public static void main(String[] args) {
 
        JFrame frame = new JFrame("CineTick Pro - Test");
        
    
        frame.setSize(400, 300);
        
   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
   
        frame.setLocationRelativeTo(null);
        
   
        JLabel label = new JLabel("Java Swing is working!", SwingConstants.CENTER);
        frame.add(label);
        
  
        frame.setVisible(true);
    }
}