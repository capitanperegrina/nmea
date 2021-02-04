package com.capitanperegrina.nmea.impl.utils;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

public class PeregrinaNMEAUtils {

    private PeregrinaNMEAUtils() {
        // Instance creation not allowed as it's a utils class.
    }

    public static PeregrinaNMEAExcutionParameters parseParameters(String[] args) {

        try {
            JSAP jsap = new JSAP();

            FlaggedOption optSerialPortName = new FlaggedOption("Serial port name. eg: /dev/gps0")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('i')
                    .setLongFlag("input");

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

            FlaggedOption optOperation = new FlaggedOption("Operation: LIST, PARSE")
                    .setStringParser(JSAP.STRING_PARSER)
                    .setRequired(true)
                    .setShortFlag('o')
                    .setLongFlag("operation");

            jsap.registerParameter(optSerialPortName);
            jsap.registerParameter(optBaudRate);
            jsap.registerParameter(optDataBits);
            jsap.registerParameter(optStopBits);
            jsap.registerParameter(optParity);
            jsap.registerParameter(optOperation);

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
                    config.getString(optSerialPortName.getID()),
                    config.getInt(optBaudRate.getID()),
                    config.getInt(optDataBits.getID()),
                    config.getInt(optStopBits.getID()),
                    config.getInt(optParity.getID()),
                    config.getString(optOperation.getID()));
        } catch (JSAPException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
