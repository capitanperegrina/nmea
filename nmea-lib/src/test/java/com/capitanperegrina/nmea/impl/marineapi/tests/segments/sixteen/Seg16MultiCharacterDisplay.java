package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class Seg16MultiCharacterDisplay extends JComponent {

    private static final int GAP = 5;

    private int segmentDisplayCount;
    private List<Seg16SegmentDisplay> segmentDisplays;
    private int scale;

    public Seg16MultiCharacterDisplay(int segmentDisplayCount, int scale) {
        this.segmentDisplayCount = segmentDisplayCount;
        segmentDisplays = new ArrayList<Seg16SegmentDisplay>(segmentDisplayCount);
        setLayout(new FlowLayout(FlowLayout.LEFT, GAP*scale, 0));
        for (int i = 0; i < segmentDisplayCount; i++) {
            Seg16SegmentDisplay display = new Seg16SegmentDisplay(scale);
            segmentDisplays.add(display);
            add(display);
        }
    }

    @Override
    public void repaint() {
        this.segmentDisplays.stream().forEach( d -> d.repaint() );
    }

    public void setText(String text) {
        if (text.length() > segmentDisplayCount) {
            text = text.substring(0, segmentDisplayCount);
        }
        int i = 0;
        for (char c : text.toCharArray()) {
            segmentDisplays.get(i++).setCharacter(c);
        }
    }
}
