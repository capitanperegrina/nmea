package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen.segments;

public enum SpecialCharsAlphabet {
    // TODO: improve this stupid solution.
    DOT('.'), // .
    COMMA(',');// ,

    private final char character;

    SpecialCharsAlphabet(char character) {
        this.character = character;
    }

    public char getChar() {
        return character;
    }

}
