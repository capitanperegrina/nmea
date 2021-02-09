package com.capitanperegrina.nmea.impl.utils;

import com.capitanperegrina.nmea.api.model.beans.BoatInformarion;
import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PeregrinaNMEAUtils {

    private static NumberFormat SPEED_FORMATTER = new DecimalFormat("#0.0");
    private static NumberFormat COURSE_FORMATTER = new DecimalFormat("000");
    private static NumberFormat DEFAULT_FORMATTER = new DecimalFormat("#0.000000");
    private static NumberFormat DEGREES_FORMATTER = new DecimalFormat("#0");
    private static NumberFormat SECONDS_FORMATTER = new DecimalFormat("#0.000");

    private PeregrinaNMEAUtils() {
        // Instance creation not allowed as it's a utils class.
    }

    public static PeregrinaNMEAExcutionParameters parseParameters(String[] args) {

        try {
            JSAP jsap = new JSAP();

            FlaggedOption optGPSSerialPortName = new FlaggedOption("Serial port name. eg: /dev/gps0 os COM4")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('i')
                    .setLongFlag("input");

            FlaggedOption optScreenSerialPortName = new FlaggedOption("ePaper screen serial port name. eg: /dev/ePaper os COM3")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('x')
                    .setLongFlag("screen");

            FlaggedOption optOperation = new FlaggedOption("Operation: LIST, PARSE")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('o')
                    .setLongFlag("operation");

            FlaggedOption optBaudRate = new FlaggedOption("Serial port baud rate. eg 4800")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("4800")
                    .setShortFlag('b')
                    .setLongFlag("baud");

            FlaggedOption optDataBits = new FlaggedOption("Data bits. eg: 8")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("8")
                    .setShortFlag('d')
                    .setLongFlag("dataBits");

            FlaggedOption optStopBits = new FlaggedOption("Stop bits. eg: 1")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("1")
                    .setShortFlag('s')
                    .setLongFlag("stopBits");

            FlaggedOption optParity = new FlaggedOption("Data bits. eg: 0")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("0")
                    .setShortFlag('p')
                    .setLongFlag("parity");

            FlaggedOption optSeconds = new FlaggedOption("Run time in seconds")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("10")
                    .setShortFlag('t')
                    .setLongFlag("time");

            FlaggedOption optScreenWidth = new FlaggedOption("Screen width in pixels")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("800")
                    .setShortFlag('w')
                    .setLongFlag("screenWidth");

            FlaggedOption optScreenHeight = new FlaggedOption("Screen height in pixels")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("600")
                    .setShortFlag('h')
                    .setLongFlag("screenHeight");

            jsap.registerParameter(optGPSSerialPortName);
            jsap.registerParameter(optScreenSerialPortName);
            jsap.registerParameter(optOperation);
            jsap.registerParameter(optBaudRate);
            jsap.registerParameter(optDataBits);
            jsap.registerParameter(optStopBits);
            jsap.registerParameter(optParity);
            jsap.registerParameter(optSeconds);
            jsap.registerParameter(optScreenWidth);
            jsap.registerParameter(optScreenHeight);

            JSAPResult config = jsap.parse(args);

            // Si se produce algún error generamos el mensaje de error con JSAP.
            if (!config.success()) {
                System.err.println();
                System.err.println("Use: java -jar PeregrinaNMEA-X.Y.Z.jar");
                System.err.println(" " + jsap.getUsage());
                System.err.println();
                System.exit(1);
            }

            return new PeregrinaNMEAExcutionParameters(
                    config.getString(optGPSSerialPortName.getID()),
                    config.getInt(optBaudRate.getID()),
                    config.getInt(optDataBits.getID()),
                    config.getInt(optStopBits.getID()),
                    config.getInt(optParity.getID()),
                    config.getString(optOperation.getID()),
                    config.getInt(optSeconds.getID()),
                    config.getString(optScreenSerialPortName.getID()),
                    config.getInt(optScreenWidth.getID()),
                    config.getInt(optScreenHeight.getID()));
        } catch (JSAPException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static Double integerPart(Double d) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        Integer intValue = bigDecimal.intValue();
        return intValue.doubleValue();
    }

    private static Double decimalPart(Double d) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        Integer intValue = bigDecimal.intValue();
        return bigDecimal.subtract(new BigDecimal(intValue)).doubleValue();
    }

    private static String coordinateFormat(Double d) {
        StringBuilder ret = new StringBuilder();
        String sign = "";
        double integerPart = integerPart(d);
        double decimalPart = decimalPart(d)*60d;
        if ( integerPart < 0 || decimalPart < 0 ) {
            sign = "-";
        }
        integerPart = Math.abs(integerPart);
        decimalPart = Math.abs(decimalPart);
        ret.append(sign)
                .append(degreesFormat(integerPart))
                .append(" ")
                .append(minutesFormat(decimalPart))
                .append("'");
        return ret.toString();
    }

    private static String speedFormat(Double d) {
        if ( d != null && d != Double.NaN ) {
            return SPEED_FORMATTER.format(d);
        }
        return "--.-";
    }

    private static String courseFormat(Double d) {
        if ( d != null && d != Double.NaN ) {
            return COURSE_FORMATTER.format(d);
        }
        return "---";
    }

    private static String defaultFormat(Double d) {
        if ( d != null && d != Double.NaN ) {
            return DEFAULT_FORMATTER.format(d);
        }
        return "-.------";
    }

    private static String degreesFormat(Double d) {
        if ( d != null && d != Double.NaN ) {
            return DEGREES_FORMATTER.format(d);
        }
        return "-";
    }

    private static String minutesFormat(Double d) {
        if ( d != null && d != Double.NaN) {
            return SECONDS_FORMATTER.format(d);
        }
        return "-.---";
    }



    public static String boatInformarionToFormattedString(BoatInformarion bi) {
        return "BoatInformarion {" +
                "date=" + bi.getDate() +
                ", latitude=" + coordinateFormat(bi.getLatitude()) +
                ", longitude=" + coordinateFormat(bi.getLongitude() ) +
                ", cog=" + courseFormat(bi.getCog()) +
                "  sog=" + speedFormat(bi.getSog()) +
                ", smoothSog=" + speedFormat(bi.getSmoothSog()) +
                ", milesFromLast=" + defaultFormat(bi.getMilesFromLast()) +
                ", hoursFromLast=" + defaultFormat(bi.getHoursFromLast()) +
                '}';
    }
}
