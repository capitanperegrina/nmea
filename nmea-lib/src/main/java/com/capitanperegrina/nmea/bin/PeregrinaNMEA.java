package com.capitanperegrina.nmea.bin;

import com.capitanperegrina.nmea.api.model.naming.KeyboardNaming;
import com.capitanperegrina.nmea.api.model.naming.WaypointsNaming;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
// import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;
import com.capitanperegrina.nmea.impl.core.PeregrinaNMEADataBuffer;
import com.capitanperegrina.nmea.impl.core.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * -i COM3 -x COM9 -o LIST
 */
public class PeregrinaNMEA implements NativeKeyListener {

    public static void main(String[] args) {
        PeregrinaNMEA me = new PeregrinaNMEA();
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);
        me.run(params);
        Runtime.getRuntime().halt(0);
    }

    private void run(PeregrinaNMEAExcutionParameters params) {
        // If keyboard is enabled initize listeners.
        if ( params.isEnableKeyboard() ) {
            // Clear previous logging configurations.
            LogManager.getLogManager().reset();

            // Get the logger for "org.jnativehook" and set the level to off.
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);

            try {
                GlobalScreen.registerNativeHook();
            }
            catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());

                System.exit(1);
            }
            GlobalScreen.addNativeKeyListener(this);
        }

        // Configuring ePaperScreen
        // PeregrinaNMEADisplay.getInstance().configure(params);

        SerialPortReader spr =  new SerialPortReader(params);

        System.out.println("Starting...");
        spr.start();
        System.out.println("Started for " + params.getSeconds() + " seconds.");
        try {
            Thread.sleep(params.getSeconds()*1000);
        } catch ( InterruptedException e ) {
            System.out.println(e.getMessage());
        }

        System.out.println("Stopping...");
        spr.stop();
        System.out.println("Stopped");
    }

    // Buggy keyboard listener methods.
    public void nativeKeyPressed(NativeKeyEvent e) {
        // Nothing to do.
//        System.out.printf("Key Pressed (0x%08X): %s\n", e.getKeyCode(), NativeKeyEvent.getKeyText(e.getKeyCode()));
//        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
//            try {
//                GlobalScreen.unregisterNativeHook();
//            } catch (NativeHookException nativeHookException) {
//                nativeHookException.printStackTrace();
//            }
//        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
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
        // System.out.printf("    0x%08X     %5d    %s\n", e.getKeyCode(), e.getKeyCode(), NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        // Nothing to do.
        // System.out.printf("Key Typed (0x%08X): %s\n", e.getKeyCode(), e.getKeyText(e.getKeyCode()));
    }
}
