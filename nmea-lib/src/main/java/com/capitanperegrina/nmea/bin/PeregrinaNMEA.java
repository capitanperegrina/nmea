package com.capitanperegrina.nmea.bin;

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

/**
 * -i COM3 -x COM9 -o LIST
 */
public class PeregrinaNMEA implements NativeKeyListener {

    public static void main(String[] args) {
        PeregrinaNMEAExcutionParameters params = PeregrinaNMEAUtils.parseParameters(args);

        // If keyboard is enabled initize listeners.
        if ( params.isEnableKeyboard() ) {
            try {
                GlobalScreen.registerNativeHook();
            }
            catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());

                System.exit(1);
            }
            GlobalScreen.addNativeKeyListener(new PeregrinaNMEA());
        }

        // Configuring ePaperScreen
        // PeregrinaNMEADisplay.getInstance().configure(params);

//        PeregrinaNMEADataBuffer.getInstance().setWaypoint(new Point(42.394486d, -8.817429d));
//        SerialPortReader spr =  new SerialPortReader(params);
//
//        System.out.println("Starting...");
//        spr.start();
        System.out.println("Started for " + params.getSeconds() + " seconds.");
        try {
            Thread.sleep(params.getSeconds()*1000);
        } catch ( InterruptedException e ) {
            System.out.println(e.getMessage());
        }
        System.out.println("Stopping...");
//        spr.stop();
        System.out.println("Stopped");

        Runtime.getRuntime().halt(0);
    }

    // Buggy keyboard listner methods.

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }
}
