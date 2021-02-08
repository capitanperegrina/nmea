package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.seven;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.SegmentDrawingHelper;
import org.javatuples.Pair;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class SevenSegmentDrawingHelper extends SegmentDrawingHelper {

    private static final Set<Character> ACCEPTED_SEGMENT_CHARS = new HashSet<>(Arrays.asList(new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}));
    private static final Set<Character> ACCEPTED_SEPARATOR_CHARS = new HashSet<>(Arrays.asList(new Character[]{'.', ',', ' '}));

    private Integer marginLeft;
    private Integer marginTop;
    private Integer horizontalSegmentWidth;
    private Integer verticalSegmentHeight;

    public SevenSegmentDrawingHelper(SerialEPaperDisplay screen, Integer characterHeight, Integer characterWidth) {
        super(screen, characterHeight, characterWidth);
        this.marginTop = new Float(.1f * Float.valueOf(this.characterHeight)).intValue();
        this.marginLeft = new Float(.1f * Float.valueOf(this.characterWidth)).intValue();
        this.horizontalSegmentWidth = this.characterWidth - 2 * this.marginLeft;
        this.verticalSegmentHeight = (this.characterHeight - 2 * this.marginTop) / 2;
    }

    public Pair<Integer, Integer> drawCharacter(Pair<Integer, Integer> offset, char character) {
        if (ACCEPTED_SEGMENT_CHARS.contains(character)) {
            return new Pair<>(drawDigit(offset.getValue0(), offset.getValue1(), character), offset.getValue1() + this.characterHeight);
        } else if (ACCEPTED_SEPARATOR_CHARS.contains(character)) {
            return new Pair<>(drawDigit(offset.getValue0(), offset.getValue1(), character), offset.getValue1() + this.characterHeight);
        }
        return offset;
    }

    private int drawDigit(Integer xOffset, Integer yOffset, char character) {
        Set<SevenSegment> segmentsToPaint = SevenNumberSergments.getNumberSegments().get(character);
        segmentsToPaint.stream().forEach(segment -> drawSegment(xOffset, yOffset, segment));
        this.screen.repaint();
        return xOffset + this.characterWidth;
    }

    private int drawSeparator(Integer xOffset, Integer yOffset, char separator) {
        int ret = xOffset + this.characterWidth / 3;
        switch (separator) {
            case ',':
                drawComma(xOffset, yOffset);
                break;
            case '.':
                drawDot(xOffset, yOffset);
                break;
            case ' ':
                ret = xOffset + this.characterWidth;
                break;
        }
        this.screen.repaint();
        return ret;
    }

    private void drawDot(Integer xOffset, Integer yOffset) {
        int radius = this.marginLeft / 2;
        this.screen.drawCircle(xOffset + this.marginLeft + radius, yOffset + this.marginTop + (this.verticalSegmentHeight * 2) - radius, radius, true);
    }

    private void drawComma(Integer xOffset, Integer yOffset) {
        drawDot(xOffset, yOffset);
        int radius = this.marginLeft / 2;
        this.screen.drawLine(xOffset + this.marginLeft + radius * 2, yOffset + this.marginTop + (this.verticalSegmentHeight * 2) - radius,
                xOffset + this.marginLeft + radius * 2 - (radius * 2), yOffset + this.marginTop + (this.verticalSegmentHeight * 2) + (radius * 2));
    }

    private void drawSegment(Integer xOffset, Integer yOffset, SevenSegment segment) {
        switch (segment) {
            case TOP:
                this.screen.drawLine(xOffset + this.marginLeft, yOffset + this.marginTop, xOffset + this.marginLeft + this.horizontalSegmentWidth, yOffset + this.marginTop);
                break;
            case LEFT_TOP:
                this.screen.drawLine(xOffset + this.marginLeft, yOffset + this.marginTop, xOffset + this.marginLeft, yOffset + this.marginTop + this.verticalSegmentHeight);
                break;
            case MIDDLE:
                this.screen.drawLine(xOffset + this.marginLeft, yOffset + this.marginTop + this.verticalSegmentHeight, xOffset + this.marginLeft + this.horizontalSegmentWidth, yOffset + this.marginTop + this.verticalSegmentHeight);
                break;
            case LEFT_BOTTOM:
                this.screen.drawLine(xOffset + this.marginLeft, yOffset + this.marginTop + this.verticalSegmentHeight, xOffset + this.marginLeft, yOffset + this.marginTop + this.verticalSegmentHeight * 2);
                break;
            case BOTTOM:
                this.screen.drawLine(xOffset + this.marginLeft, yOffset + this.marginTop + this.verticalSegmentHeight * 2, xOffset + this.marginLeft + this.horizontalSegmentWidth, yOffset + this.marginTop + this.verticalSegmentHeight * 2);
                break;
            case RIGHT_TOP:
                this.screen.drawLine(xOffset + this.marginLeft + this.horizontalSegmentWidth, yOffset + this.marginTop, xOffset + this.marginLeft + this.horizontalSegmentWidth, yOffset + this.marginTop + this.verticalSegmentHeight);
                break;
            case RIGHT_BOTTOM:
                this.screen.drawLine(xOffset + this.marginLeft + this.horizontalSegmentWidth, yOffset + this.marginTop + this.verticalSegmentHeight, xOffset + this.marginLeft + this.horizontalSegmentWidth, yOffset + this.marginTop + this.verticalSegmentHeight * 2);
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
