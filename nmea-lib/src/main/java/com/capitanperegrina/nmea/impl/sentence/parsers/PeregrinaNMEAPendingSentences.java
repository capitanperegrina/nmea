package com.capitanperegrina.nmea.impl.sentence.parsers;

import java.io.Serializable;
import java.util.Observable;

public class PeregrinaNMEAPendingSentences implements Serializable {

    public class PendingStringEvent {
        private final String pendingSentence;

        public PendingStringEvent(String pendingSentence) {
            this.pendingSentence = pendingSentence;
        }

        public String getPendingSentence() {
            return pendingSentence;
        }

        @Override
        public String toString() {
            return "PendingStringEvent{" +
                    "pendingSentence='" + pendingSentence + '\'' +
                    '}';
        }
    }

    private static final PendingNMEASentencesObservable OBSERVABLE;

    static {
        OBSERVABLE = new PendingNMEASentencesObservable();
    }

    public static Observable getObservable() {
        return OBSERVABLE;
    }

    public void addNmeaSentence(String nmeaSentence) {
        PendingStringEvent evt = new PendingStringEvent(nmeaSentence);
        synchronized (OBSERVABLE) {
            OBSERVABLE.setChanged();
            OBSERVABLE.notifyObservers(evt);
        }
    }

    private static class PendingNMEASentencesObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}