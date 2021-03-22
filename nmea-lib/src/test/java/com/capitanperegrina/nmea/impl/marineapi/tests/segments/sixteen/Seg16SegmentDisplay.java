package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.SixteenSegmentAlfabet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Seg16SegmentDisplay extends JComponent {

    public static final int WIDTH = 29;
    public static final int HEIGHT = 46;

    private static final Color activeSegmentColor = Color.BLACK;
    private static final Color inactiveSegmentColor = Color.WHITE;
    private static final long serialVersionUID = -1457613279504668851L;

    private final int scale;

    private List<Integer> lightedSegments;

    public Seg16SegmentDisplay(final int scale) {
        this.scale = scale;
        this.lightedSegments = new ArrayList<Integer>();
    }

    @Override
    public java.awt.Dimension getSize() {
        return new Dimension(WIDTH * this.scale, HEIGHT * this.scale);
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new Dimension(WIDTH * this.scale, HEIGHT * this.scale);
    }

    @Override
    public java.awt.Dimension getSize(final java.awt.Dimension rv) {
        return new Dimension(WIDTH * this.scale, HEIGHT * this.scale);
    }

    public void setCharacter(final char character) {
        this.setLightedSegments(SixteenSegmentAlfabet.MAP.get(character));
    }

    private void setLightedSegments(final SixteenSegmentAlfabet character) {
        if (character != null) {
            this.lightedSegments = Arrays.asList(character.getSegments());
        } else {
            this.lightedSegments = new ArrayList<Integer>(0);
        }
        this.repaint();
    }

    @Override
    public void paint(final Graphics g) {
        final Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(java.awt.RenderingHints.KEY_ALPHA_INTERPOLATION, java.awt.RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics.setRenderingHint(java.awt.RenderingHints.KEY_COLOR_RENDERING, java.awt.RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        //paintBackground(graphics);
        // left top to top middle
        this.paintSegment(graphics, this.lightedSegments.contains(1), getPoints(this.scale, "2,2;3,1;14,1;15,2;14,3;3,3;2,2"));
        // top middle to right top
        this.paintSegment(graphics, this.lightedSegments.contains(2), getPoints(this.scale, "15,2;16,1;27,1;28,2;27,3;16,3;15,2"));
        // left top to left middle
        this.paintSegment(graphics, this.lightedSegments.contains(3), getPoints(this.scale, "2,2;3,3;3,22;2,23;1,22;1,3;2,2"));
        // top left to middle middle
        this.paintSegment(graphics, this.lightedSegments.contains(4), getPoints(this.scale, "4,4;5,4;13,19;13,21;12,21;4,6;4,4"));
        // top middle to middle middle
        this.paintSegment(graphics, this.lightedSegments.contains(5), getPoints(this.scale, "15,2;14,3;14,22;15,23;16,22;16,3;15,2"));
        // top right to middle middle
        this.paintSegment(graphics, this.lightedSegments.contains(6), getPoints(this.scale, "25,4;26,4;26,6;18,21;17,21;17,19;25,4"));
        // top middle to right middle
        this.paintSegment(graphics, this.lightedSegments.contains(7), getPoints(this.scale, "28,2;29,3;29,22;28,23;27,22;27,3;28,2"));
        // left middle to middle middle
        this.paintSegment(graphics, this.lightedSegments.contains(8), getPoints(this.scale, "2,23;3,22;14,22;15,23;14,24;3,24;2,23"));
        // middle middle to right middle
        this.paintSegment(graphics, this.lightedSegments.contains(9), getPoints(this.scale, "15,23;16,22;27,22;28,23;27,24;16,24;15,23"));
        // left middle to bottom left
        this.paintSegment(graphics, this.lightedSegments.contains(10), getPoints(this.scale, "2,23;3,24;3,43;2,44;1,43;1,24;2,23"));
        // left bottom to middle middle
        this.paintSegment(graphics, this.lightedSegments.contains(11), getPoints(this.scale, "13,27;13,25;12,25;4,40;4,42;5,42;13,27"));
        // bottom middle to middle middle
        this.paintSegment(graphics, this.lightedSegments.contains(12), getPoints(this.scale, "15,23;16,24;16,43;15,44;14,43;14,24;15,23"));
        // bottom right to middle middle
        this.paintSegment(graphics, this.lightedSegments.contains(13), getPoints(this.scale, "17,25;18,25;26,40;26,42;25,42;17,27;17,25"));
        // right middle to bottom right
        this.paintSegment(graphics, this.lightedSegments.contains(14), getPoints(this.scale, "28,23;29,24;29,43;28,44;27,43;27,24;28,23"));
        // bottpm left to bottom middle
        this.paintSegment(graphics, this.lightedSegments.contains(15), getPoints(this.scale, "2,44;3,43;14,43;15,44;14,45;3,45;2,44"));
        // bottom middle to bottom right
        this.paintSegment(graphics, this.lightedSegments.contains(16), getPoints(this.scale, "15,44;16,43;27,43;28,44;27,45;16,45;15,44"));
        // TODO
        // paintSegment(graphics, lightedSegments.contains(16), getPoints(this.scale, "15,44;16,43;27,43;28,44;27,45;16,45;15,44"));
        // paintSegment(graphics, lightedSegments.contains(16), getPoints(this.scale, "15,44;16,43;27,43;28,44;27,45;16,45;15,44"));
    }

    private void paintSegment(final Graphics2D g, final boolean active, final Point... points) {
        if (points != null) {
            g.setColor(active ? activeSegmentColor : inactiveSegmentColor);
            final Polygon polygon = new Polygon();
            for (final Point point : points) {
                polygon.addPoint(point.x, point.y);
            }
            g.fillPolygon(polygon);
        }
    }

    private static Point[] getPoints(final int scale, final String coded) {
        if (coded.length() == 0) {
            return null;
        }
        final java.util.List<Point> pointList = new ArrayList<Point>();
        final String[] points = coded.split(";");
        for (final String point : points) {
            final String[] pointCoordinates = point.split(",");
            pointList.add(new Point(Integer.parseInt(pointCoordinates[0]) * scale, Integer.parseInt(pointCoordinates[1]) * scale));
        }
        return pointList.toArray(new Point[pointList.size()]);
    }

//    private void paintBackground(Graphics2D g) {
//        g.drawImage(BACKGROUND_IMAGE, 0, 0, this);
//    }


}
