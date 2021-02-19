package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.SixteenSegmentAlfabet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class SixteenSegmentDisplay extends JComponent {
    private java.util.List<Integer> lightedSegments;
    public static final int WIDTH = 29;
    public static final int HEIGHT = 46;

    private final double INACTIVE_COLOR_DARKEN_FRACTION = 0.4;

    private static final Image BACKGROUND_IMAGE = createBackgroundImage();

    // Highlight Colors
    private static final java.awt.Color HIGHLIGHT_COLOR_TOP = new java.awt.Color(0x000000);
    private static final java.awt.Color HIGHLIGHT_COLOR_BOTTOM = new java.awt.Color(0x625C52);

    private Color activeSegmentColor = new Color(0xFF0000);
    private Color inactiveSegmentColor = darken(new Color(0xFF0000), INACTIVE_COLOR_DARKEN_FRACTION);

    private Color backgroundColor = Color.black;

    public SixteenSegmentDisplay() {
        lightedSegments = new ArrayList<Integer>();
    }

    @Override
    public java.awt.Dimension getSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public java.awt.Dimension getSize(java.awt.Dimension rv) {
        return new Dimension(WIDTH, HEIGHT);
    }

    public void setSegmentColor(Color segmentColor) {
        activeSegmentColor = segmentColor;
        inactiveSegmentColor = darken(segmentColor, INACTIVE_COLOR_DARKEN_FRACTION);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setCharacter(char character) {
        setLightedSegments(SixteenSegmentAlfabet.MAP.get(character));
    }

    private void setLightedSegments(SixteenSegmentAlfabet character) {
        if (character != null)
            this.lightedSegments = Arrays.asList(character.getSegments());
        else
            this.lightedSegments = new ArrayList<Integer>(0);
        this.repaint();
    }

    private Color darken(Color c, double fragment) {
        Double newRed = fragment * c.getRed();
        Double newBlue = fragment * c.getBlue();
        Double newGreen = fragment * c.getGreen();
        return new Color(newRed.intValue(), newGreen.intValue(), newBlue.intValue());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(java.awt.RenderingHints.KEY_ALPHA_INTERPOLATION, java.awt.RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics.setRenderingHint(java.awt.RenderingHints.KEY_COLOR_RENDERING, java.awt.RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        paintBackground(graphics);
        // left top to top middle
        paintSegment(graphics, lightedSegments.contains(1), getPoints("2,2;3,1;14,1;15,2;14,3;3,3;2,2"));
        // top middle to right top
        paintSegment(graphics, lightedSegments.contains(2), getPoints("15,2;16,1;27,1;28,2;27,3;16,3;15,2"));
        // left top to left middle
        paintSegment(graphics, lightedSegments.contains(3), getPoints("2,2;3,3;3,22;2,23;1,22;1,3;2,2"));
        // top left to middle middle
        paintSegment(graphics, lightedSegments.contains(4), getPoints("4,4;5,4;13,19;13,21;12,21;4,6;4,4"));
        // top middle to middle middle
        paintSegment(graphics, lightedSegments.contains(5), getPoints("15,2;14,3;14,22;15,23;16,22;16,3;15,2"));
        // top right to middle middle
        paintSegment(graphics, lightedSegments.contains(6), getPoints("25,4;26,4;26,6;18,21;17,21;17,19;25,4"));
        // top middle to right middle
        paintSegment(graphics, lightedSegments.contains(7), getPoints("28,2;29,3;29,22;28,23;27,22;27,3;28,2"));
        // left middle to middle middle
        paintSegment(graphics, lightedSegments.contains(8), getPoints("2,23;3,22;14,22;15,23;14,24;3,24;2,23"));
        // middle middle to right middle
        paintSegment(graphics, lightedSegments.contains(9), getPoints("15,23;16,22;27,22;28,23;27,24;16,24;15,23"));
        // left middle to bottom left
        paintSegment(graphics, lightedSegments.contains(10), getPoints("2,23;3,24;3,43;2,44;1,43;1,24;2,23"));
        // left bottom to middle middle
        paintSegment(graphics, lightedSegments.contains(11), getPoints("13,27;13,25;12,25;4,40;4,42;5,42;13,27"));
        // bottom middle to middle middle
        paintSegment(graphics, lightedSegments.contains(12), getPoints("15,23;16,24;16,43;15,44;14,43;14,24;15,23"));
        // bottom right to middle middle
        paintSegment(graphics, lightedSegments.contains(13), getPoints("17,25;18,25;26,40;26,42;25,42;17,27;17,25"));
        // right middle to bottom right
        paintSegment(graphics, lightedSegments.contains(14), getPoints("28,23;29,24;29,43;28,44;27,43;27,24;28,23"));
        // bottpm left to bottom middle
        paintSegment(graphics, lightedSegments.contains(15), getPoints("2,44;3,43;14,43;15,4;14,45;3,45;2,44"));
        // bottom middle to bottom right
        paintSegment(graphics, lightedSegments.contains(16), getPoints("15,244;16,43;27,43;28,44;27,45;16,45;15,44"));
    }

    private void paintBackground(Graphics2D g) {
        g.drawImage(BACKGROUND_IMAGE, 0, 0, this);
    }

    private static final BufferedImage createBackgroundImage() {
        GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        final BufferedImage IMAGE = gfxConf.createCompatibleImage(WIDTH, HEIGHT, Transparency.OPAQUE);
        Graphics2D g2 = IMAGE.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        final java.awt.geom.Point2D START_INNER_BACKGROUND = new java.awt.geom.Point2D.Float(0, 0);
        final java.awt.geom.Point2D STOP_INNER_BACKGROUND = new java.awt.geom.Point2D.Float(0, HEIGHT);
        final float[] FRACTIONS_INNER_BACKGROUND =
                {
                        0.0f,
                        1.0f
                };
        final Color[] COLORS_INNER_BACKGROUND =
                {
                        new Color(0x3D3A31),
                        new Color(0x232520)
                };
        final LinearGradientPaint GRADIENT_INNER_BACKGROUND = new LinearGradientPaint(START_INNER_BACKGROUND, STOP_INNER_BACKGROUND, FRACTIONS_INNER_BACKGROUND, COLORS_INNER_BACKGROUND);
        g2.setPaint(GRADIENT_INNER_BACKGROUND);
        g2.fill(new java.awt.geom.Rectangle2D.Float(0, 0, WIDTH, HEIGHT));
        // Highlight
        g2.setColor(HIGHLIGHT_COLOR_TOP);
        g2.drawLine(0, 0, WIDTH, 0);
        g2.setColor(HIGHLIGHT_COLOR_BOTTOM);
        g2.drawLine(0, HEIGHT, WIDTH, HEIGHT);
        g2.dispose();
        return IMAGE;
    }

    private void paintSegment(Graphics2D g, boolean active, Point... points) {
        if (points != null) {
            g.setColor(active ? activeSegmentColor : inactiveSegmentColor);
            Polygon polygon = new Polygon();
            for (Point point : points) {
                polygon.addPoint(point.x, point.y);
            }
            g.fillPolygon(polygon);
        }
    }

    private static Point[] getPoints(String coded) {
        if(coded.length() == 0)
        {
            return null;
        }
        java.util.List<Point> pointList = new ArrayList<Point>();
        String[] points = coded.split(";");
        for (String point : points) {
            String[] pointCoordinates = point.split(",");
            pointList.add(new Point(Integer.parseInt(pointCoordinates[0]), Integer.parseInt(pointCoordinates[1])));
        }
        return pointList.toArray(new Point[pointList.size()]);
    }
}
