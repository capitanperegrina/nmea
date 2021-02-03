package com.capitanperegrina.nmea.examples;

import com.capitanperegrina.nmea.impl.serialportreader.SerialPortReader;
import com.capitanperegrina.nmea.impl.serialportreader.listener.SerialPortReaderListener;
import jssc.SerialPort;
import jssc.SerialPortException;

public class DevReader {

    static SerialPort serialPort;


    private static void run() {

        SerialPortReader spr =  new SerialPortReader("/dev/ttyUSB0", 800,8,1,0);
        try {
            Thread.sleep(10000);
        } catch ( InterruptedException e ) {
            System.out.println(e.getMessage());
        }
        spr.stop();

//        try (BufferedReader in = new BufferedReader(new FileReader("/dev/ttyUSB0")) ) {
//            int leido;
//            do {
//                leido = in.read();
//                System.out.print(leido + " ");
//            } while ( leido != -1 );
//            System.out.println();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try (Stream<String> stream = Files.lines(Paths.get("/dev/ttyUSB0"))) {
//            stream.forEach(System.out::println);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        String line = null;
//        while(true) {
//
//            InputStream in;
//            try ( BufferedReader bsr = new BufferedReader(new FileReader("/dev/ttyUSB0"))) {
//                while((line = bsr.readLine()) != null) {
//                    System.out.println(line);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void main(String[] args) {
        run();
    }
}
