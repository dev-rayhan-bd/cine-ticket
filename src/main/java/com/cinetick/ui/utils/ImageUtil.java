package com.cinetick.ui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageUtil {

    public static ImageIcon loadAndScale(String urlPath, int width, int height) {
        try {
            URL url = new URL(urlPath);
            BufferedImage img = ImageIO.read(url);
            return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (Exception e) { return null; }
    }

    public static ImageIcon getCircleImage(File file, int size) {
        try {
            BufferedImage master = ImageIO.read(file);
            
            //  (Center Crop)
            int width = master.getWidth();
            int height = master.getHeight();
            int diameter = Math.min(width, height);
            int x = (width - diameter) / 2;
            int y = (height - diameter) / 2;
            BufferedImage cropped = master.getSubimage(x, y, diameter, diameter);

            BufferedImage mask = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = mask.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.fill(new Ellipse2D.Double(0, 0, size, size));
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(cropped, 0, 0, size, size, null);
            g2.dispose();

            return new ImageIcon(mask);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon fromFile(File file, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(file);
            return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (Exception e) { return null; }
    }
}