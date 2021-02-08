package com.capitanperegrina.nmea.impl.epaper.drawing.segmentNumber.sixteen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MultiCharacterDisplay extends JComponent {
    private static final int GAP = 5;
    private int segmentDisplayCount;
    private List<SixteenSegmentDisplay> segmentDisplays;

    public MultiCharacterDisplay(int segmentDisplayCount) {
        this(segmentDisplayCount, Color.red);
    }

    public MultiCharacterDisplay(int segmentDisplayCount, Color segmentColor) {
        this.segmentDisplayCount = segmentDisplayCount;
        segmentDisplays = new ArrayList<SixteenSegmentDisplay>(segmentDisplayCount);
        setLayout(new FlowLayout(FlowLayout.LEFT, GAP, 0));
        for (int i = 0; i < segmentDisplayCount; i++) {
            SixteenSegmentDisplay display = new SixteenSegmentDisplay();
            display.setSegmentColor(segmentColor);
            segmentDisplays.add(display);
            add(display);
        }
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

    public static void main(String[] args) {
        String text = "Hola mundo";

        MultiCharacterDisplay display = new MultiCharacterDisplay(text.length(), Color.GREEN);
        display.setText(text);

        JFrame frame = new JFrame();
        frame.setBackground(Color.black);
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(display);
        frame.pack();

        frame.setVisible(true);
    }
}