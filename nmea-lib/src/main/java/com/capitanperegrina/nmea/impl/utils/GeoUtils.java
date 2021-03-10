package com.capitanperegrina.nmea.impl.utils;

import java.text.DecimalFormat;
import java.util.Locale;

public class GeoUtils {

    private static final String DEGREES_SEPARATOR = "   ";
    private static final String MINUTES_SEPARATOR = "'   ";
    private static final String SECONDS_SEPARATOR = "\"   ";
    public static final DecimalFormat DEGREES_FORMATTER = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
    public static final DecimalFormat MINUTES_FORMATTER_DM = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());

    static {
        DEGREES_FORMATTER.setMinimumFractionDigits(8);
        DEGREES_FORMATTER.setMinimumFractionDigits(8);
        MINUTES_FORMATTER_DM.setMinimumFractionDigits(3);
        MINUTES_FORMATTER_DM.setMinimumFractionDigits(3);
    }

    public static String toLatitudeDM(final Double d) {
        return toCoordinateDM(d) + getSufixForLatitude(d);
    }

    public static String toLatitudeDMS(final Double d) {
        return toCoordinateDMS(d) + getSufixForLatitude(d);
    }

    public static String toLongitudeDM(final Double d) {
        return toCoordinateDM(d) + getSufixForLongitude(d);
    }

    public static String toLongitudeDMS(final Double d) {
        return toCoordinateDMS(d) + getSufixForLongitude(d);
    }

    private static String toCoordinateDM(final Double d) {
        final Double dd = Math.abs(d);
        final StringBuilder sb = new StringBuilder();
        final int degrees = dd.intValue();
        final double minutes = (dd.doubleValue() - degrees) * 60;
        sb.append(degrees).append(DEGREES_SEPARATOR).append(MINUTES_FORMATTER_DM.format(minutes)).append(MINUTES_SEPARATOR);
        return sb.toString();
    }

    private static String toCoordinateDMS(final Double d) {
        final Double dd = Math.abs(d);
        final StringBuilder sb = new StringBuilder();
        final int degrees = dd.intValue();
        final Double tmp = (dd.doubleValue() - degrees) * 60;
        final int minutes = tmp.intValue();
        final int seconds = Double.valueOf((tmp - minutes) * 60).intValue();
        sb.append(degrees).append(DEGREES_SEPARATOR).append(minutes).append(MINUTES_SEPARATOR).append(seconds).append(SECONDS_SEPARATOR);
        return sb.toString();
    }

    private static String getSufixForLatitude(final Double d) {
        return d >= 0 ? "N" : "S";
    }

    private static String getSufixForLongitude(final Double d) {
        return d >= 0 ? "E" : "W";
    }

//    public static void main(final String[] args) {
//        System.out.println(toLatitudeDM(-42.323456d) + " " + toLongitudeDM(-8.6767674d));
//        System.out.println(toLatitudeDMS(42.323456d) + " " + toLongitudeDMS(8.6767674d));
//    }
}
