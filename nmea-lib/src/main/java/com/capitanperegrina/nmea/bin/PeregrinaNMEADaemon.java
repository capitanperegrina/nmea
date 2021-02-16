package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.PeregrinaNMEAOperations;
import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import com.capitanperegrina.nmea.api.model.naming.KeyboardNaming;
import com.capitanperegrina.nmea.api.model.naming.WaypointsNaming;
import com.capitanperegrina.nmea.api.model.service.ITrackService;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import com.capitanperegrina.nmea.impl.core.serialportreader.SerialPortReader;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Component
public class PeregrinaNMEADaemon implements NativeKeyListener {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PeregrinaNMEADaemon.class);

    @Autowired
    private SerialPortReader spr;

    @Autowired
    private ITrackService trackService;

    public void run(PeregrinaNMEAExcutionParameters params) {
        if (PeregrinaNMEAOperations.EXPORT.toString().equals(params.getOperation())) {
            this.trackService.generateGpxFile();
        } else if (PeregrinaNMEAOperations.RESET.toString().equals(params.getOperation())) {
            this.trackService.cleanData();
        } else {
            doGpsOperations(params);
        }
    }

    private void doGpsOperations(PeregrinaNMEAExcutionParameters params) {
        try {
            if ( params.isEnableKeyboard() ) {
                // If keyboard is enabled initize listeners.
                configureKeyboard();
            }

            // Configuring ePaperScreen
            // PeregrinaNMEADisplay.getInstance().configure(params);

            // Configuring ePaperScreen
            PeregrinaNMEADataBuffer.getInstance().setTrackService(this.trackService);
            this.spr.configure(params);

            spr.start();
            LOGGER.info("Started for {} seconds.", params.getSeconds());
            Thread.sleep(params.getSeconds()*1000);

            spr.stop();
        } catch ( InterruptedException e ) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    private void configureKeyboard() {
        // Clear previous logging configurations.
        LogManager.getLogManager().reset();

        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException e) {
            LOGGER.error("There was a problem registering the native hook.", e);
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    // Buggy keyboard listener methods.
    public void nativeKeyReleased(NativeKeyEvent e) {
        LOGGER.debug(String.format("    0x%08X     %5d    %s\n", e.getKeyCode(), e.getKeyCode(), NativeKeyEvent.getKeyText(e.getKeyCode())));
        if ( e.getKeyCode() == KeyboardNaming.PLUS ) {
            if (PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() < WaypointsNaming.getInternalWaypoints().size() - 1) {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() + 1);
            } else {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(0);
            }
        } else if ( e.getKeyCode() == KeyboardNaming.MINUS ) {
            if ( PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() > 0 ) {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(PeregrinaNMEADataBuffer.getInstance().getCurrentWaypoint() - 1);
            } else {
                PeregrinaNMEADataBuffer.getInstance().setCurrentWaypoint(WaypointsNaming.getInternalWaypoints().size() - 1);
            }
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        // Nothing to do.
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        // Nothing to do.
    }
}