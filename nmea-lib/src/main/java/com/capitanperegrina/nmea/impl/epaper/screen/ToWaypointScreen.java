package com.capitanperegrina.nmea.impl.epaper.screen;

import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.epaper.screen.components.ScreenComponent;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import org.javatuples.Pair;
import tk.schmid.epaper.display.protocol.DisplayFontSize;

import java.util.Objects;

public class ToWaypointScreen {

    private static final int COG_LABEL_X = 60;
    private static final int COG_LABEL_Y = 50;
    private static final int SOG_LABEL_X = 480;
    private static final int SOG_LABEL_Y = 50;
    private static final int DTW_LABEL_X = 60;
    private static final int DTW_LABEL_Y = 350;
    private static final int VMC_LABEL_X = 480;
    private static final int VMC_LABEL_Y = 350;

    private static final int COG_X = 60;
    private static final int COG_Y = 90;
    private static final int SOG_X = 480;
    private static final int SOG_Y = 90;
    private static final int DTW_X = 60;
    private static final int DTW_Y = 390;
    private static final int VMC_X = 480;
    private static final int VMC_Y = 390;
    private static final int DESTINO_X = 10;
    private static final int DESTINO_Y = 550;

    private static final int ZONA_COG_X0 = 0;
    private static final int ZONA_COG_Y0 = 85;
    private static final int ZONA_COG_X1 = 399;
    private static final int ZONA_COG_Y1 = 299;

    private static final int ZONA_SOG_X0 = 401;
    private static final int ZONA_SOG_Y0 = 85;
    private static final int ZONA_SOG_X1 = 800;
    private static final int ZONA_SOG_Y1 = 299;

    private static final int ZONA_DTW_X0 = 0;
    private static final int ZONA_DTW_Y0 = 460;
    private static final int ZONA_DTW_X1 = 399;
    private static final int ZONA_DTW_Y1 = 549;

    private static final int ZONA_VMC_X0 = 401;
    private static final int ZONA_VMC_Y0 = 460;
    private static final int ZONA_VMC_X1 = 800;
    private static final int ZONA_VMC_Y1 = 549;

    private static final int ZONA_DESTINO_X0 = 10;
    private static final int ZONA_DESTINO_Y0 = 550;
    private static final int ZONA_DESTINO_X1 = 582;
    private static final int ZONA_DESTINO_Y1 = 800;

    private ScreenComponent cog;
    private ScreenComponent sog;
    private ScreenComponent dtw;
    private ScreenComponent vmc;
    private ScreenComponent destination;

    private boolean painting = false;
    private boolean repaintScreen = false;

    public void init() {
        PeregrinaNMEADisplay.getInstance().clearScreen();
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().setEnglishFontSize(DisplayFontSize.DotMatrix_32);
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().displayText(COG_LABEL_X, COG_LABEL_Y, "COG");
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().displayText(SOG_LABEL_X, SOG_LABEL_Y, "SOG");
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().displayText(DTW_LABEL_X, DTW_LABEL_Y, "DTW");
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().displayText(VMC_LABEL_X, VMC_LABEL_Y, "VMC");
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().drawLine(0, 300, 800, 300);
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().drawLine(400, 0, 400, 600);
    }

    public void repaint() {
        if (this.repaintScreen && !this.painting) {
            this.painting = true;

            if (!this.cog.isDisplayed()) {
                PeregrinaNMEADisplay.getInstance().clearZone(ZONA_COG_X0, ZONA_COG_Y0, ZONA_COG_X1, ZONA_COG_Y1);
                PeregrinaNMEADisplay.getInstance().draw7simpleSegments(this.cog.getOffset(), this.cog.getScale(), this.cog.getValue());
                this.cog.setDisplayed(true);
            }

            if (!this.sog.isDisplayed()) {
                PeregrinaNMEADisplay.getInstance().clearZone(ZONA_SOG_X0, ZONA_SOG_Y0, ZONA_SOG_X1, ZONA_SOG_Y1);
                PeregrinaNMEADisplay.getInstance().draw7simpleSegments(this.sog.getOffset(), this.sog.getScale(), this.sog.getValue());
                this.sog.setDisplayed(true);
            }

            if (!this.dtw.isDisplayed()) {
                PeregrinaNMEADisplay.getInstance().clearZone(ZONA_DTW_X0, ZONA_DTW_Y0, ZONA_DTW_X1, ZONA_DTW_Y1);
                PeregrinaNMEADisplay.getInstance().draw7simpleSegments(this.dtw.getOffset(), this.dtw.getScale(), this.dtw.getValue());
                this.dtw.setDisplayed(true);
            }

            if (!this.vmc.isDisplayed()) {
                PeregrinaNMEADisplay.getInstance().clearZone(ZONA_VMC_X0, ZONA_VMC_Y0, ZONA_VMC_X1, ZONA_VMC_Y1);
                PeregrinaNMEADisplay.getInstance().draw7simpleSegments(this.vmc.getOffset(), this.vmc.getScale(), this.vmc.getValue());
                this.vmc.setDisplayed(true);
            }

            if (!this.destination.isDisplayed()) {
                PeregrinaNMEADisplay.getInstance().clearZone(ZONA_DESTINO_X0, ZONA_DESTINO_Y0, ZONA_DESTINO_X1, ZONA_DESTINO_Y1);
                PeregrinaNMEADisplay.getInstance().getePaperDisplay().displayText(5, 550, this.destination.getValue());
                this.destination.setDisplayed(true);
            }

            this.repaintScreen = false;
            PeregrinaNMEADisplay.getInstance().repaint();
            this.painting = false;
        }
    }

    public ToWaypointScreen() {
        this.cog = new ScreenComponent(new Pair<>(COG_X, COG_Y), 3, "", "", false);
        this.sog = new ScreenComponent(new Pair<>(SOG_X, SOG_Y), 3, "", "", false);
        this.dtw = new ScreenComponent(new Pair<>(DTW_X, DTW_Y), 3, "", "", false);
        this.vmc = new ScreenComponent(new Pair<>(VMC_X, VMC_Y), 3, "", "", false);
        this.destination = new ScreenComponent(new Pair<>(DESTINO_X, DESTINO_Y), null, "", "", false);
    }

    public void putCog(final Integer newCog) {
        if (!this.cog.getValue().equals(newCog)) {
            this.cog.setOldValue(this.sog.getValue());
            this.cog.setValue(PeregrinaNMEAUtils.formatCourse(newCog));
            this.cog.setDisplayed(Objects.equals(this.cog.getValue(), this.cog.getOldValue()));
        }
        this.repaintScreen = this.repaintScreen || !this.cog.isDisplayed();
    }

    public void putSog(final Double newSog) {
        if (!this.sog.getValue().equals(newSog)) {
            this.sog.setOldValue(this.sog.getValue());
            this.sog.setValue(PeregrinaNMEAUtils.formatSpeed(newSog));
            this.sog.setDisplayed(Objects.equals(this.sog.getValue(), this.sog.getOldValue()));
        }
        this.repaintScreen = this.repaintScreen || !this.sog.isDisplayed();
    }

    public void putDtw(final Double newDtw) {
        if (!this.dtw.getValue().equals(newDtw)) {
            this.dtw.setOldValue(this.dtw.getValue());
            this.dtw.setValue(PeregrinaNMEAUtils.formatDistance(newDtw));
            this.dtw.setDisplayed(Objects.equals(this.dtw.getValue(), this.dtw.getOldValue()));
        }
        this.repaintScreen = this.repaintScreen || !this.dtw.isDisplayed();
    }

    public void putVmc(final Double newVmc) {
        if (!this.vmc.getValue().equals(newVmc)) {
            this.vmc.setOldValue(this.vmc.getValue());
            this.vmc.setValue(PeregrinaNMEAUtils.formatSpeed(newVmc));
            this.vmc.setDisplayed(Objects.equals(this.vmc.getValue(), this.vmc.getOldValue()));
        }
        this.repaintScreen = this.repaintScreen || !this.vmc.isDisplayed();
    }

    public void putDestination(final String newDestination) {
        if (!this.destination.getValue().equals(newDestination)) {
            this.destination.setOldValue(this.destination.getValue());
            this.destination.setValue(newDestination);
            this.destination.setDisplayed(false);
            this.destination.setDisplayed(Objects.equals(this.destination.getValue(), this.destination.getOldValue()));
        }
        this.repaintScreen = this.repaintScreen || !this.destination.isDisplayed();
    }

    public ScreenComponent getCog() {
        return this.cog;
    }

    public void setCog(final ScreenComponent cog) {
        this.cog = cog;
    }

    public ScreenComponent getSog() {
        return this.sog;
    }

    public void setSog(final ScreenComponent sog) {
        this.sog = sog;
    }

    public ScreenComponent getDtw() {
        return this.dtw;
    }

    public void setDtw(final ScreenComponent dtw) {
        this.dtw = dtw;
    }

    public ScreenComponent getVmc() {
        return this.vmc;
    }

    public void setVmc(final ScreenComponent vmc) {
        this.vmc = vmc;
    }

    public ScreenComponent getDestination() {
        return this.destination;
    }

    public void setDestination(final ScreenComponent destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "ToWaypointScreen{" +
                "\ncog=" + this.cog.toString() +
                ",\n sog=" + this.sog.toString() +
                ",\n dtw=" + this.dtw.toString() +
                ",\n vmc=" + this.vmc.toString() +
                ",\n destination=" + this.destination +
                '}';
    }
}
