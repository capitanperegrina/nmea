package com.capitanperegrina.nmea.impl.marineapi.tests;

import java.io.File;
import java.io.FileInputStream;

import com.capitanperegrina.nmea.impl.sentence.parsers.listeners.GGAListener;
import com.capitanperegrina.nmea.impl.sentence.parsers.listeners.GSAListener;
import com.capitanperegrina.nmea.impl.sentence.parsers.listeners.GSVListener;
import com.capitanperegrina.nmea.impl.sentence.parsers.listeners.RMCListener;
import net.sf.marineapi.nmea.io.SentenceReader;

public class MarineApiTest {
    public static void main(String[] args) {
        File file = new File("c:/tmp/nmea.log");
        try {
            SentenceReader reader = new SentenceReader(new FileInputStream(file));
            reader.addSentenceListener(new GGAListener()); // GPGGA
            reader.addSentenceListener(new GSAListener()); // GPGSA
            reader.addSentenceListener(new GSVListener()); // GPGSV
            reader.addSentenceListener(new RMCListener()); // GPRMC
            reader.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
