package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay;

import com.capitanperegrina.nmea.impl.epaper.drawing.DrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming.SegmentsNaming;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.segments.SpecialCharsAlphabet;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.slf4j.LoggerFactory;
import tk.schmid.epaper.display.EPaperDisplay;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public abstract class SegmentDrawingHelper extends DrawingHelper {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SegmentDrawingHelper.class);

    private static final String NOT_PROPPERLY_DECLARED_COMPONENT = "Not propperly declared component: {}";

    private final DecimalFormat decimalFormatter;
    private final DecimalFormatSymbols doubleFormatterSymbols;

    public SegmentDrawingHelper(final EPaperDisplay screen) {
        super(screen);
        this.decimalFormatter = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
        this.decimalFormatter.setMinimumFractionDigits(2);
        this.decimalFormatter.setMaximumFractionDigits(2);
        this.doubleFormatterSymbols = this.decimalFormatter.getDecimalFormatSymbols();
    }

    @Override
    public Pair<Integer, Integer> drawString(final Pair<Integer, Integer> offsetStart, final String text, final int scale) {
        final Pair<Integer, Integer> ret = new Pair<>(offsetStart.getValue0(), offsetStart.getValue1());
        if (StringUtils.isNotEmpty(text)) {
            for (int i = 0; i < text.length(); i++) {
                // This is a nasty trick to use dot or comma segments.
                SpecialCharsAlphabet specialChar = null;
                if (i + 1 < text.length() && text.charAt(i + 1) == this.doubleFormatterSymbols.getDecimalSeparator()) {
                    specialChar = SpecialCharsAlphabet.valueOf(this.doubleFormatterSymbols.getDecimalSeparator() + "");
                    i++;
                }
                final Pair<Integer, Integer> charNewOffset = this.drawCharacter(offsetStart, text.charAt(i), specialChar, scale);
                ret.setAt0(ret.getValue0() + charNewOffset.getValue0());
                ret.setAt1(charNewOffset.getValue1());
            }
        }
        return ret;
    }

    public Pair<Integer, Integer> drawDouble(final Pair<Integer, Integer> offsetStart, final Double d, final int scale) {
        final Pair<Integer, Integer> ret = new Pair<>(offsetStart.getValue0(), offsetStart.getValue1());
        if (d != null && d != Double.NaN) {
            return this.drawString(offsetStart, this.decimalFormatter.format(d), scale);
        }
        return this.drawString(offsetStart, "-" + this.doubleFormatterSymbols.getDecimalSeparator() + "--", 5);
    }

    public abstract Pair<Integer, Integer> drawCharacter(Pair<Integer, Integer> offsetStart, char character, SpecialCharsAlphabet specialChar, int scale);

    public abstract void clearZoneForDigit(Pair<Integer, Integer> offsetStart, int scale);

    protected void drawComponents(final Pair<Integer, Integer> offsetStart, final List<SegmentComponent> segmentComponents, final int scale) {
        segmentComponents.stream().forEach(segmentComponent -> {
            this.drawComponent(offsetStart, segmentComponent, scale);
        });
    }

    private void drawComponent(final Pair<Integer, Integer> offsetStart, final SegmentComponent segmentComponent, final int scale) {
        this.clearZoneForDigit(offsetStart, scale);
        switch (segmentComponent.getPolygonType()) {
            case TRIANGLE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 3) {
                    final int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    final int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    final int x1 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    final int y1 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(1).getValue1() * scale;
                    final int x2 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(2).getValue0() * scale;
                    final int y2 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(2).getValue1() * scale;
                    this.screen.drawTriangle(x0, y0, x1, y1, x2, y2, SegmentsNaming.FILLED);
                } else {
                    LOGGER.error(NOT_PROPPERLY_DECLARED_COMPONENT, segmentComponent.toString());
                }
                break;
            case RECTANGLE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 2) {
                    final int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    final int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    final int x1 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    final int y1 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(1).getValue1() * scale;
                    this.screen.drawRectangle(x0, y0, x1, y1, SegmentsNaming.FILLED);
                } else {
                    LOGGER.error(NOT_PROPPERLY_DECLARED_COMPONENT, segmentComponent.toString());
                }
                break;
            case CIRCLE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 2) {
                    final int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    final int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    final int r = segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    this.screen.drawCircle(x0, y0, r, SegmentsNaming.FILLED);
                } else {
                    LOGGER.error(NOT_PROPPERLY_DECLARED_COMPONENT, segmentComponent.toString());
                }
                break;
            case LINE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 2) {
                    final int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    final int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    final int x1 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    final int y1 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(1).getValue1() * scale;
                    this.screen.drawLine(x0, y0, x1, y1);
                } else {
                    LOGGER.error(NOT_PROPPERLY_DECLARED_COMPONENT, segmentComponent.toString());
                }
                break;
            default:
                break;
        }
    }

    public void clearZone(final Pair<Integer, Integer> offsetStart, final Pair<Integer, Integer> offsetEnd) {
        this.screen.drawRectangle(
                offsetStart.getValue0(),
                offsetStart.getValue1(),
                offsetEnd.getValue0(),
                offsetEnd.getValue1(),
                true
        );
    }


}
