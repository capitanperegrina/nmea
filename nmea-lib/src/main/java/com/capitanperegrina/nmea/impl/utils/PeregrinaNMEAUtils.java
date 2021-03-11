package com.capitanperegrina.nmea.impl.utils;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.martiansoftware.jsap.*;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class PeregrinaNMEAUtils {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PeregrinaNMEAUtils.class);

//    private static final NumberFormat DEFAULT_FORMATTER = new DecimalFormat("#0.00000");
//    private static final NumberFormat DEFAULT_PARSEABLE_FORMATTER = DecimalFormat.getInstance(Locale.US);
//    private static final NumberFormat SPEED_FORMATTER = new DecimalFormat("#0.0");
//    private static final NumberFormat COURSE_FORMATTER = new DecimalFormat("000");
//    private static final NumberFormat DEGREES_FORMATTER = new DecimalFormat("#0");
//    private static final NumberFormat SECONDS_FORMATTER = new DecimalFormat("#0.000");

    private static final DecimalFormat SPEED_FORMATTER;
    private static final DecimalFormat DISTANCE_FORMATTER;
    private static final DecimalFormat COURSE_FORMATTER;

    static {
//        DEFAULT_PARSEABLE_FORMATTER.setMaximumFractionDigits(5);
        SPEED_FORMATTER = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
        DISTANCE_FORMATTER = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
        COURSE_FORMATTER = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());

    }

    private PeregrinaNMEAUtils() {
        // Instance creation not allowed as it's a utils class.
    }

    private static void string2File(final String filename, final String content) {
        try {
            final File outFile = new File(filename);
            final FileWriter out = new FileWriter(outFile);
            out.write(content);
            out.close();
        } catch (final IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static String file2String(final String filePath) {
        final StringBuilder ret = new StringBuilder("");
        final File file = new File(filePath);

        if (!file.exists()) {
            LOGGER.warn("File {} does not exist. ", filePath);
        } else {
            if (!file.canRead()) {
                LOGGER.warn("File {} cannot be readed. ", filePath);
            } else {
                ret.append(lee(file));
            }
        }
        return ret.toString();
    }

    private static String lee(final File file) {
        try {
            final StringBuilder readed = new StringBuilder();
            final FileReader reader = new FileReader(file);
            int readedChar = reader.read();
            while (readedChar >= 0) {
                final char charCaracter = (char) readedChar;
                readed.append(charCaracter);
                readedChar = reader.read();
            }
            reader.close();
            return readed.toString();
        } catch (final FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (final IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 2021-02-20 12:50:04
     *
     * @return
     * @throws IOException
     */
    public static String getLastBootTime() throws IOException {
        LOGGER.trace("getLastBootTime()");
        final Process uptimeProc = Runtime.getRuntime().exec("uptime -S");
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()))) {
            final String ret = in.readLine();
            LOGGER.info("Last boot time: {} ", ret);
            return ret;
        }
    }

    public static PeregrinaNMEAExcutionParameters parseParameters(final String[] args) {

        try {
            final JSAP jsap = new JSAP();

            final FlaggedOption optGPSSerialPortName = new FlaggedOption("Serial port name. eg: /dev/gps0 os COM4")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('i')
                    .setLongFlag("input");

            final FlaggedOption optScreenSerialPortName = new FlaggedOption("ePaper screen serial port name. eg: /dev/ePaper os COM3")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('x')
                    .setLongFlag("screen");

            final FlaggedOption optOperation = new FlaggedOption("Operation: LIST, PARSE")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('o')
                    .setLongFlag("operation");

            final FlaggedOption optBaudRate = new FlaggedOption("Serial port baud rate. eg 4800")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("4800")
                    .setShortFlag('b')
                    .setLongFlag("baud");

            final FlaggedOption optDataBits = new FlaggedOption("Data bits. eg: 8")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("8")
                    .setShortFlag('d')
                    .setLongFlag("dataBits");

            final FlaggedOption optStopBits = new FlaggedOption("Stop bits. eg: 1")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("1")
                    .setShortFlag('s')
                    .setLongFlag("stopBits");

            final FlaggedOption optParity = new FlaggedOption("Data bits. eg: 0")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("0")
                    .setShortFlag('p')
                    .setLongFlag("parity");

            final FlaggedOption optSeconds = new FlaggedOption("Run time in seconds")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("-1")
                    .setShortFlag('t')
                    .setLongFlag("time");

            final FlaggedOption optScreenWidth = new FlaggedOption("Screen width in pixels")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("800")
                    .setShortFlag('w')
                    .setLongFlag("screenWidth");

            final FlaggedOption optScreenHeight = new FlaggedOption("Screen height in pixels")
                    .setStringParser(JSAP.INTEGER_PARSER)
                    .setRequired(false)
                    .setDefault("600")
                    .setShortFlag('h')
                    .setLongFlag("screenHeight");

            final Switch optEnableKeyboard = new Switch("Enable keyboard input")
                    .setShortFlag('k')
                    .setLongFlag("enableKeyboard");

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
            jsap.registerParameter(optEnableKeyboard);

            final JSAPResult config = jsap.parse(args);

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
                    config.getInt(optScreenHeight.getID()),
                    config.getBoolean(optEnableKeyboard.getID()));
        } catch (final JSAPException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static String formatDistance(final Double distance) {
        final Double d = round(distance);
        int digits = 2;
        if (d != null && d.doubleValue() >= 10) {
            digits = 1;
        }
        final DecimalFormat formatter = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
        formatter.setMinimumFractionDigits(digits);
        formatter.setMaximumFractionDigits(digits);
        if (d != null && !d.equals(Double.NaN)) {
            return formatter.format(d);
        }
        return "-,--";
    }

    public static String formatSpeed(final Double speed) {
        final Double s = round(speed);
        int digits = 2;
        if (s != null && (s.doubleValue() < 0d || s.doubleValue() >= 10d)) {
            digits = 1;
        }
        final DecimalFormat formatter = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
        formatter.setMinimumFractionDigits(digits);
        formatter.setMaximumFractionDigits(digits);
        if (s != null && !s.equals(Double.NaN)) {
            // Spanish ñapa
            final String ret = formatter.format(s);
            if (ret.equals("-0.0")) {
                return "0.00";
            }
            if (ret.equals("-0,0")) {
                return "0,00";
            }
            return ret;
        }
        return "-,--";
    }

    public static String formatCourse(final Integer c) {
        final DecimalFormat formatter = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.getDefault());
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(0);
        formatter.setMinimumIntegerDigits(3);
        return formatter.format(c);
    }

    public static double round(final double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

//    public static Double defaultPrecision(Double d) {
//        if (d==null) {
//            return null;
//        }
//        if (d.equals(Double.NaN)) {
//            return Double.NaN;
//        }
//        return Double.parseDouble(DEFAULT_PARSEABLE_FORMATTER.format(d));
//    }

//    private static Double integerPart(final Double d) {
//        final BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
//        final Integer intValue = bigDecimal.intValue();
//        return intValue.doubleValue();
//    }
//
//    private static Double decimalPart(final Double d) {
//        final BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
//        final Integer intValue = bigDecimal.intValue();
//        return bigDecimal.subtract(new BigDecimal(intValue)).doubleValue();
//    }
//
//    private static String coordinateFormat(final Double d) {
//        final StringBuilder ret = new StringBuilder();
//        String sign = "";
//        double integerPart = integerPart(d);
//        double decimalPart = decimalPart(d) * 60d;
//        if (integerPart < 0 || decimalPart < 0) {
//            sign = "-";
//        }
//        integerPart = Math.abs(integerPart);
//        decimalPart = Math.abs(decimalPart);
//        ret.append(sign)
//                .append(degreesFormat(integerPart))
//                .append(" ")
//                .append(minutesFormat(decimalPart))
//                .append("'");
//        return ret.toString();
//    }
//
//
//
//    public static String speedFormat(final Double d) {
//        if (d != null && !d.equals(Double.NaN)) {
//            return SPEED_FORMATTER.format(d);
//        }
//        return "--.-";
//    }
//
//    private static String courseFormat(final Double d) {
//        if (d != null && !d.equals(Double.NaN)) {
//            return COURSE_FORMATTER.format(d);
//        }
//        return "---";
//    }
//
//    private static String defaultFormat(final Double d) {
//        if (d != null && !d.equals(Double.NaN)) {
//            return DEFAULT_FORMATTER.format(d);
//        }
//        return "-.------";
//    }
//
//    private static String degreesFormat(final Double d) {
//        if (d != null && !d.equals(Double.NaN)) {
//            return DEGREES_FORMATTER.format(d);
//        }
//        return "-";
//    }
//
//    private static String minutesFormat(final Double d) {
//        if (d != null && !d.equals(Double.NaN)) {
//            return SECONDS_FORMATTER.format(d);
//        }
//        return "-.---";
//    }
//
//    public static String boatInformarionToFormattedString(final BoatPosition bi) {
//        return "BoatPosition {" +
//                "date=" + bi.getDate() +
//                ", latitude=" + coordinateFormat(bi.getLatitude()) +
//                ", longitude=" + coordinateFormat(bi.getLongitude()) +
//                '}';
//    }
}