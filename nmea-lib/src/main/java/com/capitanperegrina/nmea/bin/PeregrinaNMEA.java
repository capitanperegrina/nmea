package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * -i COM3 -x COM9 -o LIST
 */
public class PeregrinaNMEA {
    public static void main(String[] args) {
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.capitanperegrina");
        PeregrinaNMEADaemon daemon = ctx.getBean(PeregrinaNMEADaemon.class);
        daemon.run(params);
        Runtime.getRuntime().halt(0);
    }
}
