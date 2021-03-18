package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Seg16Test {

    public static void main(String[] args) {
        String text = "275";
        Seg16MultiCharacterDisplay display = (new Seg16MultiCharacterDisplay(text.length(), 3));
        display.setText(text);

        JFrame frame = new JFrame();
        frame.setBackground(Color.WHITE);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 600));
        frame.setUndecorated(true);
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);
        BufferedImage img = getScreenShot(frame);

        try{
            ImageIO.write(
                img,
                "bmp",
                new File("screenshot.bmp"));
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        display.setText("290");
        frame.getContentPane().removeAll();
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);
        img = getScreenShot(frame);
        frame.dispose();

        try{
            ImageIO.write(
                img,
                "bmp",
                new File("screenshot2.bmp"));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getScreenShot(Component component) {
        BufferedImage image = new BufferedImage(
                component.getWidth(),
                component.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        // call the Component's paint method, using the Graphics object of the image.
        component.paint(image.getGraphics());
        return image;
    }

}
