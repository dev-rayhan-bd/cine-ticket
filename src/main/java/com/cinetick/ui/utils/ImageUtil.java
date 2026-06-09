package com.cinetick.ui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageUtil {
    public static ImageIcon loadAndScale(String urlPath, int width, int height) {
        try {
            URL url = new URL(urlPath);
            BufferedImage img = ImageIO.read(url);
            Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            return null; // Fallback to empty if fails
        }
    }
}