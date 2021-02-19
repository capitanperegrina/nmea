package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tk.schmid.epaper.display.serialcom.EPaperCommunicationException;

/**
 * -i COM3 -x COM9 -o LIST
 */
public class PeregrinaNMEA {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeregrinaNMEA.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Configuring...");
            PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);
            ApplicationContext ctx = new AnnotationConfigApplicationContext("com.capitanperegrina");
            PeregrinaNMEADaemon daemon = ctx.getBean(PeregrinaNMEADaemon.class);
            LOGGER.info("Starting...");
            daemon.run(params);
            LOGGER.info("Stopping...");
        } catch ( Throwable e ) {
            // Catch absolutelly everything because people like me, code shit.
            LOGGER.error(e.getMessage(), e);
        } finally {
            Runtime.getRuntime().halt(0);
        }
    }
}
