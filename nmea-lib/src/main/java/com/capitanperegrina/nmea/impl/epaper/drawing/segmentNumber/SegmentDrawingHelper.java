package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class SegmentDrawingHelper {

    private static final Set<Character> ACCEPTED_SEGMENT_CHARS = new HashSet<>(Arrays.asList(new Character[] {'0','1','2','3','4','5','6','7','8','9'}));
    private static final Set<Character> ACCEPTED_SEPARATOR_CHARS = new HashSet<>(Arrays.asList(new Character[] {'.',','}));

    private SerialEPaperDisplay screen;
    private Integer height;
    private Integer width;
    private Integer left;
    private Integer top;
    private Integer h;
    private Integer w;

    public SegmentDrawingHelper(SerialEPaperDisplay screen, Integer height, Integer width) {
        this.screen = screen;
        this.height = height;
        this.width = width;
        this.top = new Float( .1f * Float.valueOf(this.height) ).intValue();
        this.left = new Float( .1f * Float.valueOf(this.width) ).intValue();
        this.w = this.width - 2 * this.left;
        this.h = ( this.height - 2 * this.top ) / 2;
    }

    public int drawDigit( Integer xOffset, Integer yOffset, char character) {
        if ( ACCEPTED_SEGMENT_CHARS.contains(character) ) {
            Set<Segment> segmentsToPaint = NumberSergments.getNumberSegments().get(character);
            segmentsToPaint.stream().forEach(segment -> drawSegment(xOffset, yOffset, segment));
            return xOffset + this.width;
        } else if ( ACCEPTED_SEPARATOR_CHARS.contains(character) ) {

            return xOffset + this.width/3;
        }
        return xOffset;
    }

    private void drawSegment( Integer xOffset, Integer yOffset, Segment segment ) {
        switch (segment) {
            case TOP:
                this.screen.drawLine(xOffset+this.left, yOffset+this.top,xOffset+this.left+this.w,yOffset+this.top);
                break;
            case LEFT_TOP:
                this.screen.drawLine(xOffset+this.left, yOffset+this.top,xOffset+this.left,yOffset+this.top+this.h);
                break;
            case MIDDLE:
                this.screen.drawLine(xOffset+this.left, yOffset+this.top+this.h,xOffset+this.left+this.w,yOffset+this.top+this.h);
                break;
            case LEFT_BOTTOM:
                this.screen.drawLine(xOffset+this.left, yOffset+this.top+this.h,xOffset+this.left,yOffset+this.top+this.h*2);
                break;
            case BOTTOM:
                this.screen.drawLine(xOffset+this.left, yOffset+this.top+this.h*2,xOffset+this.left+this.w,yOffset+this.top+this.h*2);
                break;
            case RIGHT_TOP:
                this.screen.drawLine(xOffset+this.left+this.w, yOffset+this.top,xOffset+this.left+this.w,yOffset+this.top+this.h);
                break;
            case RIGHT_BOTTOM:
                this.screen.drawLine(xOffset+this.left+this.w, yOffset+this.top+this.h,xOffset+this.left+this.w,yOffset+this.top+this.h*2);
                break;
            case DOT:
                break;
            case COMMA:
                break;
        }
        this.screen.repaint();
    }
}
