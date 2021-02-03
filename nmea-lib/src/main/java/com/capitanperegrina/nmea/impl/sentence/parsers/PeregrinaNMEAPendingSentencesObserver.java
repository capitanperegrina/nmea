package com.capitanperegrina.nmea.impl.sentence.parsers;

import com.capitanperegrina.nmea.impl.sentence.parsers.listeners.GLLListener;
import com.capitanperegrina.nmea.impl.sentence.parsers.listeners.RMCListener;
import com.capitanperegrina.nmea.impl.sentence.parsers.listeners.VTGListener;
import net.sf.marineapi.nmea.io.SentenceReader;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

public class PeregrinaNMEAPendingSentencesObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if ( arg instanceof PeregrinaNMEAPendingSentences.PendingStringEvent) {
            PeregrinaNMEAPendingSentences.PendingStringEvent evt = (PeregrinaNMEAPendingSentences.PendingStringEvent) arg;
            String nmea = evt.getPendingSentence();
            if (StringUtils.isNotEmpty(nmea)) {
                try (InputStream targetStream = new ByteArrayInputStream(nmea.getBytes()) ) {
                    SentenceReader reader = new SentenceReader(targetStream);
                    // reader.addSentenceListener(new GGAListener()); // GPGGA
                    // reader.addSentenceListener(new GSAListener()); // GPGSA
                    // reader.addSentenceListener(new GSVListener()); // GPGSV
                    reader.addSentenceListener(new RMCListener()); // GPRMC
                    reader.addSentenceListener(new GLLListener()); // GPGLL
                    reader.addSentenceListener(new VTGListener()); // GPVTG
                    reader.start();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
