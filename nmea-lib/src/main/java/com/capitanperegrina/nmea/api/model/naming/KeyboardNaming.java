package com.capitanperegrina.nmea.api.model.naming;

public class KeyboardNaming {

    public static final int ESCAPE = 0x00000001;  // 1    Escape
    public static final int TAB = 0x0000000F;     // 15    Tabulador

    public static final int DOT = 0x00000053;     // .
    public static final int ZERO = 0x0000000B;    // 0
    public static final int ONE = 0x00000002;     // 1
    public static final int TWO = 0x00000003;     // 2
    public static final int THREE = 0x00000004;   // 3
    public static final int FOUR = 0x00000005;    // 4
    public static final int FIVE = 0x00000006;    // 5
    public static final int SIX = 0x00000007;     // 6
    public static final int SEVEN = 0x00000008;   // 7
    public static final int EIGHT = 0x00000009;   // 8
    public static final int NINE = 0x0000000A;    // 9
    public static final int ENTER = 0x0000001C;   // ENTER
    public static final int PLUS = 0x00000E4E;    // +
    public static final int MINUS = 0x00000E4A;   // -
    public static final int DIVIDE = 0x00000035;  // /
    public static final int TIMES = 0x00000E37;   // *

    public static final int INSERT = 0x00000E52; // Insertar
    public static final int DEL = 0x00000E53; // Suprimir
    public static final int END = 0x00000E4F; // Fin
    public static final int DOWN = 0x0000E050; // Abajo
    public static final int PAGE_DOWN = 0x00000E51; // AvPág
    public static final int LEFT = 0x0000E04B; // Izquierda
    public static final int RIGHT = 0x0000E04D; // Derecha
    public static final int HOME = 0x00000E47; // Inicio
    public static final int UP = 0x0000E048; // Arriba
    public static final int PAGE_UP = 0x00000E49; // RePág
    public static final int BACKSPACE = 0x0000000E; // Retroceso

    private KeyboardNaming() {
        // Not instantiable as it's a naming class
    }
}
