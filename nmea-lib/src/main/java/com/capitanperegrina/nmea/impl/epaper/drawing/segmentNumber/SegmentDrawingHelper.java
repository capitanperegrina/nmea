package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class SegmentDrawingHelper {

    private static final Set<Character> ACCEPTED_SEGMENT_CHARS = new HashSet<>(Arrays.asList(new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}));
    private static final Set<Character> ACCEPTED_SEPARATOR_CHARS = new HashSet<>(Arrays.asList(new Character[]{'.', ',',' '}));

    private final SerialEPaperDisplay screen;
    private final Integer height;
    private final Integer width;
    private final Integer left;
    private final Integer top;
    private final Integer h;
    private final Integer w;

    public SegmentDrawingHelper(SerialEPaperDisplay screen, Integer height, Integer width) {
        this.screen = screen;
        this.height = height;
        this.width = width;
        this.top = new Float(.1f * Float.valueOf(this.height)).intValue();
        this.left = new Float(.1f * Float.valueOf(this.width)).intValue();
        this.w = this.width - 2 * this.left;
        this.h = (this.height - 2 * this.top) / 2;
    }

    public int drawCharacter(Integer xOffset, Integer yOffset, char character) {
        if (ACCEPTED_SEGMENT_CHARS.contains(character)) {
            return drawDigit(xOffset, yOffset, character);
        } else if (ACCEPTED_SEPARATOR_CHARS.contains(character)) {
            return drawSeparator(xOffset, yOffset, character);
        }
        return xOffset;
    }

    private int drawDigit(Integer xOffset, Integer yOffset, char character) {
        Set<Segment> segmentsToPaint = NumberSergments.getNumberSegments().get(character);
        segmentsToPaint.stream().forEach(segment -> drawSegment(xOffset, yOffset, segment));
        this.screen.repaint();
        return xOffset + this.width;
    }

    private int drawSeparator(Integer xOffset, Integer yOffset, char separator) {
        int ret = xOffset+this.width/3;
        switch ( separator ) {
            case ',':
                drawComma(xOffset,yOffset);
                break;
            case '.':
                drawDot(xOffset,yOffset);
                break;
            case ' ':
                ret = xOffset+this.width;
                break;
        }
        this.screen.repaint();
        return ret;
    }

    private void drawDot(Integer xOffset, Integer yOffset) {
        int radius = this.left / 2;
        this.screen.drawCircle(xOffset + this.left + radius, yOffset + this.top + (this.h * 2) - radius, radius, true);
    }

    private void drawComma(Integer xOffset, Integer yOffset) {
        drawDot(xOffset, yOffset);
        int radius = this.left / 2;
        this.screen.drawLine(xOffset + this.left + radius * 2, yOffset + this.top + (this.h * 2) - radius,
                xOffset + this.left + radius * 2 - (radius * 2), yOffset + this.top + (this.h * 2) + (radius * 2));
    }

    private void drawSegment(Integer xOffset, Integer yOffset, Segment segment) {
        switch (segment) {
            case TOP:
                this.screen.drawLine(xOffset + this.left, yOffset + this.top, xOffset + this.left + this.w, yOffset + this.top);
                break;
            case LEFT_TOP:
                this.screen.drawLine(xOffset + this.left, yOffset + this.top, xOffset + this.left, yOffset + this.top + this.h);
                break;
            case MIDDLE:
                this.screen.drawLine(xOffset + this.left, yOffset + this.top + this.h, xOffset + this.left + this.w, yOffset + this.top + this.h);
                break;
            case LEFT_BOTTOM:
                this.screen.drawLine(xOffset + this.left, yOffset + this.top + this.h, xOffset + this.left, yOffset + this.top + this.h * 2);
                break;
            case BOTTOM:
                this.screen.drawLine(xOffset + this.left, yOffset + this.top + this.h * 2, xOffset + this.left + this.w, yOffset + this.top + this.h * 2);
                break;
            case RIGHT_TOP:
                this.screen.drawLine(xOffset + this.left + this.w, yOffset + this.top, xOffset + this.left + this.w, yOffset + this.top + this.h);
                break;
            case RIGHT_BOTTOM:
                this.screen.drawLine(xOffset + this.left + this.w, yOffset + this.top + this.h, xOffset + this.left + this.w, yOffset + this.top + this.h * 2);
                break;
            case DOT:
                break;
            case COMMA:
                break;
        }
    }

    public SerialEPaperDisplay getScreen() {
        return screen;
    }
}
