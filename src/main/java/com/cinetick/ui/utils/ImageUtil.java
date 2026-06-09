package com.cinetick.ui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageUtil {
 
    public static ImageIcon loadAndScale(String urlPath, int width, int height) {
        try {
            URL url = new URL(urlPath);
            BufferedImage img = ImageIO.read(url);
            return scaleImage(img, width, height);
        } catch (Exception e) { return null; }
    }

    //(Profile Preview)
    public static ImageIcon fromFile(File file, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(file);
            return scaleImage(img, width, height);
        } catch (Exception e) { return null; }
    }

    private static ImageIcon scaleImage(BufferedImage img, int width, int height) {
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}