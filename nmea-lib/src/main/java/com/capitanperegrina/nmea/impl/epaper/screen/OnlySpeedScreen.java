package com.capitanperegrina.nmea.impl.epaper.screen;

import com.capitanperegrina.nmea.impl.epaper.PeregrinaNMEADisplay;
import com.capitanperegrina.nmea.impl.epaper.screen.components.ScreenComponent;
import com.capitanperegrina.nmea.impl.utils.PeregrinaNMEAUtils;
import org.javatuples.Pair;
import tk.schmid.epaper.display.protocol.DisplayFontSize;

import java.util.Objects;

public class OnlySpeedScreen {

    private static final int SOG_LABEL_X = 155;
    private static final int SOG_LABEL_Y = 145;

    private static final int SOG_X = 155;
    private static final int SOG_Y = 195;

    private static final int ZONA_SOG_X0 = 155;
    private static final int ZONA_SOG_Y0 = 195;
    private static final int ZONA_SOG_X1 = 700;
    private static final int ZONA_SOG_Y1 = 450;

    private ScreenComponent sog;

    private boolean painting = false;
    private boolean repaintScreen = false;

    public void init() {
        PeregrinaNMEADisplay.getInstance().clearScreen();
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().setEnglishFontSize(DisplayFontSize.DotMatrix_32);
        PeregrinaNMEADisplay.getInstance().getePaperDisplay().displayText(SOG_LABEL_X, SOG_LABEL_Y, "SOG");
    }

    public void repaint() {
        if (this.repaintScreen && !this.painting) {
            this.painting = true;

            if (!this.sog.isDisplayed()) {
                PeregrinaNMEADisplay.getInstance().clearZone(ZONA_SOG_X0, ZONA_SOG_Y0, ZONA_SOG_X1, ZONA_SOG_Y1);
                PeregrinaNMEADisplay.getInstance().draw7simpleSegments(this.sog.getOffset(), this.sog.getScale(), this.sog.getValue());
            }

            this.repaintScreen = false;
            PeregrinaNMEADisplay.getInstance().repaint();
            this.painting = false;
        }
    }

    public OnlySpeedScreen() {
        this.sog = new ScreenComponent(new Pair<>(SOG_X, SOG_Y), 3, "", "", false);
    }

    public void putSog(final Double newSog) {
        if (!this.sog.getValue().equals(newSog)) {
            this.sog.setOldValue(this.sog.getValue());
            this.sog.setValue(PeregrinaNMEAUtils.formatSpeed(newSog));
            this.sog.setDisplayed(Objects.equals(this.sog.getValue(), this.sog.getOldValue()));
        }
        this.repaintScreen = this.repaintScreen || !this.sog.isDisplayed();
    }

    public ScreenComponent getSog() {
        return this.sog;
    }

    public void setSog(final ScreenComponent sog) {
        this.sog = sog;
    }

    @Override
    public String toString() {
        return "OnlySpeedScreen{" +
                "sog=" + this.sog +
                ", painting=" + this.painting +
                ", repaintScreen=" + this.repaintScreen +
                '}';
    }
}
