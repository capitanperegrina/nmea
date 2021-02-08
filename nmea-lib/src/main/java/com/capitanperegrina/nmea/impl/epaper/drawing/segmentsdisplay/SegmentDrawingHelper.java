package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay;

import com.capitanperegrina.nmea.impl.epaper.drawing.DrawingHelper;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.components.SegmentComponent;
import com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming.SegmentsNaming;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import tk.schmid.epaper.display.EPaperDisplay;

import java.util.List;

public abstract class SegmentDrawingHelper extends DrawingHelper {

    public SegmentDrawingHelper(EPaperDisplay screen) {
        super(screen);
    }

    public Pair<Integer, Integer> drawString(Pair<Integer, Integer> offsetStart, String text, int scale) {
        Pair<Integer,Integer> ret = new Pair<>(offsetStart.getValue0(), offsetStart.getValue1());
        if (StringUtils.isNotEmpty(text)) {
            for ( int i=0 ; i < text.length() ; i++ ) {
                Pair<Integer,Integer> charNewOffset = drawCharacter(offsetStart, text.charAt(i), scale );
                ret.setAt0( ret.getValue0() + charNewOffset.getValue0() );
                ret.setAt1( charNewOffset.getValue1());
            }
        }
        return ret;
    }

    public abstract Pair<Integer,Integer> drawCharacter(Pair<Integer,Integer> offsetStart, char character, int scale);

    protected void drawComponents(Pair<Integer,Integer> offsetStart, List<SegmentComponent> segmentComponents, int scale) {
        segmentComponents.stream().forEach(segmentComponent -> {
            drawComponent(offsetStart, segmentComponent, scale);
        });
    }

    private void drawComponent(Pair<Integer,Integer> offsetStart, SegmentComponent segmentComponent, int scale) {
        switch (segmentComponent.getPolygonType()) {
            case TRIANGLE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 3) {
                    int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    int x1 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    int y1 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(1).getValue1() * scale;
                    int x2 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(2).getValue0() * scale;
                    int y2 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(2).getValue1() * scale;
                    this.screen.drawTriangle(x0, y0, x1, y1, x2, y2, SegmentsNaming.FILLED);
                } else {
                    System.err.println("Not fully declared component: " + segmentComponent.toString());
                }
                break;
            case RECTANGLE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 2) {
                    int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    int x1 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    int y1 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(1).getValue1() * scale;
                    this.screen.drawRectangle(x0, y0, x1, y1, SegmentsNaming.FILLED);
                } else {
                    System.err.println("Not fully declared component: " + segmentComponent.toString());
                }
                break;
            case CIRCLE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 2) {
                    int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    int r = segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    this.screen.drawCircle(x0, y0, r, SegmentsNaming.FILLED);
                } else {
                    System.err.println("Not fully declared component: " + segmentComponent.toString());
                }
                break;
            case LINE:
                if (CollectionUtils.isNotEmpty(segmentComponent.getListaCoordenadas()) && segmentComponent.getListaCoordenadas().size() == 2) {
                    int x0 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(0).getValue0() * scale;
                    int y0 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(0).getValue1() * scale;
                    int x1 = offsetStart.getValue0() + segmentComponent.getListaCoordenadas().get(1).getValue0() * scale;
                    int y1 = offsetStart.getValue1() + segmentComponent.getListaCoordenadas().get(1).getValue1() * scale;
                    this.screen.drawLine(x0, y0, x1, y1);
                } else {
                    System.err.println("Not fully declared component: " + segmentComponent.toString());
                }
                break;
            default:
                break;
        }
    }
}
