package com.capitanperegrina.nmea.impl.epaper.drawing.segmentsdisplay.sixteen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SixteenSegmentAlfabet {
        A(new char[]{'a', 'A'}, 1, 2, 3, 7, 8, 9, 10, 14),
        B(new char[]{'b', 'B'}, 1, 2, 5, 7, 9, 12, 14, 15, 16),
        C(new char[]{'c', 'C'}, 1, 2, 3, 10, 15, 16),
        D(new char[]{'d', 'D'}, 1, 2, 5, 7, 12, 14, 15, 16),
        E(new char[]{'e', 'E'}, 1, 2, 3, 8, 9, 10, 15, 16),
        F(new char[]{'f', 'F'}, 1, 2, 3, 8, 10),
        G(new char[]{'g', 'G'}, 1, 2, 3, 9, 10, 14, 15, 16),
        H(new char[]{'h', 'H'}, 3, 7, 8, 9, 10, 14),
        I(new char[]{'i', 'I'}, 1, 2, 5, 12, 15, 16),
        J(new char[]{'j', 'J'}, 7, 10, 14, 15, 16),
        K(new char[]{'k', 'K'}, 3, 6, 8, 10, 13),
        L(new char[]{'l', 'L'}, 3, 10, 15, 16),
        M(new char[]{'m', 'M'}, 3, 4, 6, 7, 10, 14),
        N(new char[]{'n', 'N'}, 3, 4, 7, 10, 13, 14),
        O(new char[]{'o', 'O'}, 1, 2, 3, 7, 10, 14, 15, 16),
        P(new char[]{'p', 'P'}, 1, 2, 3, 6, 8, 10),
        Q(new char[]{'q', 'Q'}, 1, 2, 3, 7, 10, 13, 14, 15, 16),
        R(new char[]{'r', 'R'}, 1, 2, 3, 7, 8, 9, 10, 13),
        S(new char[]{'s', 'S'}, 1, 2, 3, 8, 9, 14, 15, 16),
        T(new char[]{'t', 'T'}, 1, 2, 5, 12),
        U(new char[]{'u', 'U'}, 3, 7, 10, 14, 15, 16),
        V(new char[]{'v', 'V'}, 3, 6, 10, 11),
        W(new char[]{'w', 'W'}, 3, 7, 10, 11, 13, 14),
        X(new char[]{'x', 'X'}, 4, 6, 11, 13),
        Y(new char[]{'y', 'Y'}, 4, 6, 12),
        Z(new char[]{'z', 'Z'}, 1, 2, 6, 11, 15, 16),
        BLANK(new char[]{' '}, null),
        ZERO(new char[]{'0'}, 1, 2, 3, 4, 7, 10, 13, 14, 15, 16),
        ONE(new char[]{'1'}, 7, 14),
        TWO(new char[]{'2'}, 1, 2, 7, 8, 9, 10, 15, 16),
        THREE(new char[]{'3'}, 1, 2, 7, 9, 14, 15, 16),
        FOUR(new char[]{'4'}, 3, 7, 8, 9, 14),
        FIVE(new char[]{'5'}, 1, 2, 3, 8, 13, 15, 16),
        SIX(new char[]{'6'}, 1, 2, 3, 8, 9, 10, 14, 15, 16),
        SEVEN(new char[]{'7'}, 1, 2, 7, 14),
        EIGHT(new char[]{'8'}, 1, 2, 3, 7, 8, 9, 10, 14, 15, 16),
        NINE(new char[]{'9'}, 1, 2, 3, 7, 8, 9, 14, 15, 16),
        DEGREE(new char[]{'º'}, 1, 3, 5, 8),
        MINUTES(new char[]{'\''}, 5),
        SECONDS(new char[]{'"'}, 3, 5),
        COMMA(new char[]{','}, 11),
        DOT(new char[]{'.'}, 12);

        private final Integer[] segments;
        private final char[] characters;

        public static final Map<Character, SixteenSegmentAlfabet> MAP;

        static {
            MAP = new HashMap<Character, SixteenSegmentAlfabet>(36);
            for (SixteenSegmentAlfabet alfabet : SixteenSegmentAlfabet.values()) {
                for (char character : alfabet.characters) {
                    MAP.put(character, alfabet);
                }
            }
        }

        SixteenSegmentAlfabet(char[] characters, Integer... segments) {
            this.characters = characters;
            this.segments = segments;
        }

        private Integer[] getSegments() {
                return segments;
        }

        public static List<Integer> getSegments(char character) {
                return Arrays.asList(MAP.get(character).getSegments());
        }
    }