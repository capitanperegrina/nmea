package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.seven;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SevenSegmentsAlphabet {
    BLANK(new char[]{' '}, null),
    ZERO(new char[]{'0'}, 1, 2, 3, 5, 6, 7),
    ONE(new char[]{'1'}, 3, 6),
    TWO(new char[]{'2'}, 1, 3, 4, 5, 7),
    THREE(new char[]{'3'}, 1, 3, 4, 6, 7),
    FOUR(new char[]{'4'}, 2, 3, 4, 6),
    FIVE(new char[]{'5'}, 1, 2, 4, 6, 7),
    SIX(new char[]{'6'}, 1, 2, 4, 5, 6, 7),
    SEVEN(new char[]{'7'}, 1, 2, 3, 6),
    EIGHT(new char[]{'8'}, 1, 2, 3, 4, 5, 6, 7),
    NINE(new char[]{'9'}, 1, 2, 3, 4, 6, 7),
    DEGREE(new char[]{'#'}, 1, 2, 3, 4),
    MINUTES(new char[]{'\''}, 2),
    SECONDS(new char[]{'"'}, 2, 3),
    MINUS(new char[]{'-'}, 4),
    DOT(new char[]{'.'}, 8),
    COMMA(new char[]{','}, 9);

    private final Integer[] segments;
    private final char[] characters;

    public static final Map<Character, SevenSegmentsAlphabet> MAP;

    static {
        MAP = new HashMap<Character, SevenSegmentsAlphabet>(36);
        for (final SevenSegmentsAlphabet alfabet : SevenSegmentsAlphabet.values()) {
            for (final char character : alfabet.characters) {
                MAP.put(character, alfabet);
            }
        }
    }

    SevenSegmentsAlphabet(final char[] characters, final Integer... segments) {
        this.characters = characters;
        this.segments = segments;
    }

    private Integer[] getSegments() {
        return this.segments;
    }

    public static Integer[] getSegments(final char name) {
        Integer[] ret = new Integer[]{};
        if (MAP.get(name) != null) {
            ret = MAP.get(name).getSegments();
        }
        return ret;
//        for (final SpecialCharsAlphabet oneChar : SpecialCharsAlphabet.values()) {
//            if (oneChar.asChar == name) {
//                return oneChar;
//            }
//        }
//        return null;
    }
}
