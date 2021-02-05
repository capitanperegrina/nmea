package com.capitanperegrina.nmea.impl.epaper;

import org.apache.commons.lang3.StringUtils;

import com.capitanperegrina.nmea.api.model.beans.PeregrinaNMEAExcutionParameters;
import tk.schmid.epaper.display.protocol.DisplayFontSize;
import tk.schmid.epaper.display.serialcom.SerialEPaperDisplay;

public class PeregrinaNMEADisplay {

    private static volatile PeregrinaNMEADisplay singleton;

    private SerialEPaperDisplay screen;
    private Integer screenWidth;
    private Integer screenHeight;

    //private constructor.
    private PeregrinaNMEADisplay(){
        if (singleton != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static PeregrinaNMEADisplay getInstance() {
        if (singleton == null) {
            synchronized (PeregrinaNMEADisplay.class) {
                if (singleton == null) singleton = new PeregrinaNMEADisplay();
            }
        }
        return singleton;
    }

    public void configure(PeregrinaNMEAExcutionParameters params) {
        this.screen = new SerialEPaperDisplay(params.getScreenSerialPortName());
        this.screenHeight = params.getScreenHeight();
        this.screenWidth = params.getScreenWidth();
        this.screen.connect();
        System.out.println("Connected...");
        System.out.println("Active storage: " + screen.getActiveStorage());
        System.out.println("Display Direction: " + screen.getDisplayDirection());
        System.out.println("English Font Size: " + screen.getEnglishFontSize());
        System.out.println("Chinese Font Size " + screen.getChineseFontSize());
        System.out.println("Drawing Color: " + screen.getDrawingColor());
        System.out.println("Background Color: " + screen.getBackgroundColor());
        System.out.println("Baud Rate: " + screen.getBaudRate());
    }

    public SerialEPaperDisplay getScreen() {
        return screen;
    }

    public void clearScreen() {
        if ( this.screen != null ) {
            this.screen.clearScreen();
        }
    }



/*    public void printCentered(String text) {
        String showText = text;
        if ( this.screen != null ) {
            screen.setEnglishFontSize(DisplayFontSize.DotMatrix_64);

            // Reduce text length id necessary
            int textLenghInPixels = showText.length() * screen.getEnglishFontSize().getDisplayFontSizeConstant();
            while ( textLenghInPixels > screenWidth ) {
                showText = StringUtils.chop(showText);
                textLenghInPixels = showText.length() * screen.getEnglishFontSize().getDisplayFontSizeConstant();
            }

            screen.getEnglishFontSize().getFontPixelHeight()

            // Calculate how to center text.
            int margin = ( this.screenWidth - textLenghInPixels ) / 2;
            screen.displayText(300, 260, text);
        }
    }


    /*


		display.setEnglishFontSize(DisplayFontSize.DotMatrix_32);
		display.displayText(100, 360, "https://github.com/SchmidChristian/ePaperDriver");

		display.drawCircle(410, 160, 50, true);

		display.repaint();

     */
}