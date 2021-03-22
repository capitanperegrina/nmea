package com.capitanperegrina.nmea.impl.marineapi.tests.segments.sixteen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Seg16MultiCharacterDisplay extends JComponent {

    private static final int GAP = 5;
    private static final long serialVersionUID = -2420046483208485873L;

    private final List<Seg16SegmentDisplay> segmentDisplays = new ArrayList<>();
    private int scale;

    public Seg16MultiCharacterDisplay(final String text, final int scale) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, GAP * scale, 0));
        for (int i = 0; i < text.length(); i++) {
            final Seg16SegmentDisplay display = new Seg16SegmentDisplay(scale);
            display.setBackground(Color.WHITE);
            this.segmentDisplays.add(display);
            this.add(display);
        }
        this.setText(text);
    }

    @Override
    public void repaint() {
        this.segmentDisplays.stream().forEach(d -> d.repaint());
    }

    private void setText(final String text) {
        int i = 0;
        for (final char c : text.toCharArray()) {
            this.segmentDisplays.get(i++).setCharacter(c);
        }
    }
}
