package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.naming;

public enum SpecialCharsAlphabet {
    // TODO: improve this stupid solution.
    DOT('.'), // .
    COMMA(',');// ,

    private final char asChar;

    SpecialCharsAlphabet(final char asChar) {
        this.asChar = asChar;
    }

    public char getChar() {
        return this.asChar;
    }

    public char asChar() {
        return this.asChar;
    }

    public static SpecialCharsAlphabet getSpecialChar(final char name) {
        for (final SpecialCharsAlphabet oneChar : SpecialCharsAlphabet.values()) {
            if (oneChar.asChar == name) {
                return oneChar;
            }
        }
        return null;
    }
}
