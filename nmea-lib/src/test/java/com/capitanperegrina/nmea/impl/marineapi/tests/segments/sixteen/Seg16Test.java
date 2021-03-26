package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;

public class Seg16Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Seg16Test.class);

    private final static String TEXT_1 = "275";
    private final static String TEXT_2 = "290";

    public static void save(final BufferedImage img, final String type, final String filename) {
        try {
            ImageIO.write(img, type, new File(filename));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage generateImage(final String text, final int scale) {
        final Seg16MultiCharacterDisplay display = new Seg16MultiCharacterDisplay(text, scale);
        final BufferedImage img = ScreenImage.createImage(display);
        LOGGER.info("Image generated for text \"{}\", (Size: {},{})", text, img.getWidth(), img.getHeight());
        return img;
    }

    public static void main(final String[] args) {
        save(generateImage(TEXT_1, 3), "bmp", "screenshot1.bmp");
        save(generateImage(TEXT_2, 3), "bmp", "screenshot2.bmp");
    }
}
