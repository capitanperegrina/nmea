package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.PeregrinaNMEAOperations;
import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.api.model.naming.KeyboardNaming;
import com.capitanperegrina.nmea.api.model.naming.WaypointsNaming;
import com.capitanperegrina.nmea.api.model.service.ITrackService;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import com.capitanperegrina.nmea.impl.core.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Component
public class PeregrinaNMEADaemon implements NativeKeyListener {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PeregrinaNMEADaemon.class);

    private static final int ESCAPE_TIMES_TO_STOP = 3;

    @Autowired
    private SerialPortReader spr;

    @Autowired
    private ITrackService trackService;

    private int escapeTimes = 0;

    public void run(final PeregrinaNMEAExcutionParameters params) {
        if (PeregrinaNMEAOperations.EXPORT.toString().equals(params.getOperation())) {
            this.trackService.generateGpxFile();
        } else if (PeregrinaNMEAOperations.RESET.toString().equals(params.getOperation())) {
            this.trackService.cleanData();
        } else {
            this.doGpsOperations(params);
        }
    }

    private void doGpsOperations(final PeregrinaNMEAExcutionParameters params) {
        try {
            if (params.isEnableKeyboard()) {
                // If keyboard is enabled initize listeners.
                this.configureKeyboard();
            }

            // Configuring ePaperScreen
            PeregrinaNMEADisplay.getInstance().configure(params);

            // Configuring data buffer
            PeregrinaNMEADataBuffer.getInstance().setTrackService(this.trackService);
            this.spr.configure(params);
            this.spr.start();
            if (params.getSeconds() != -1) {
                LOGGER.info("Started for {} seconds.", params.getSeconds());
                Thread.sleep(params.getSeconds() * 1000);
            } else {
                LOGGER.info("Started forever.");
                while (true) {
                    Thread.sleep(Long.MAX_VALUE);
                }
            }
        } catch (final InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void configureKeyboard() {
        try {
            LOGGER.debug("If application stops here and you're running on linux, maybe you should ensure exists org/jnativehook/lib/linux/arm/libJNativeHook.so in jnativehook-2.1.0.jar.");
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (final NativeHookException e) {
            LOGGER.error("There was a problem registering the native hook.", e);
            throw new RuntimeException(e);
        }
    }

    // Buggy keyboard listener methods.
    @Override
    public void nativeKeyReleased(final NativeKeyEvent e) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("    0x%08X     %5d    %s\n", e.getKeyCode(), e.getKeyCode(), NativeKeyEvent.getKeyText(e.getKeyCode())));
        }

        if (e.getKeyCode() == KeyboardNaming.ESCAPE) {
            this.escapeTimes++;
            if (this.escapeTimes == ESCAPE_TIMES_TO_STOP) {
                Runtime.getRuntime().halt(0);
            }
        } else {
            this.escapeTimes = 0;
        }

        if (e.getKeyCode() == KeyboardNaming.PLUS) {
            if (PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() < WaypointsNaming.getInternalWaypoints().size() - 1) {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() + 1);
            } else {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(0);
            }
        } else if (e.getKeyCode() == KeyboardNaming.MINUS) {
            if (PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() > 0) {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() - 1);
            } else {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(WaypointsNaming.getInternalWaypoints().size() - 1);
            }
        }
    }

    @Override
    public void nativeKeyPressed(final NativeKeyEvent e) {
        // Nothing to do.
    }

    @Override
    public void nativeKeyTyped(final NativeKeyEvent e) {
        // Nothing to do.
    }

    @PostConstruct
    public void init() {
        // Clear previous logging configurations.
        LogManager.getLogManager().reset();

        // Get the logger for "org.jnativehook" and set the level to off.
        final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
    }
}
