package com.capitanperegrina.nmea.api.model.naming;

import com.capitanperegrina.nmea.api.model.beans.mapelements.elements.Point;

import java.util.ArrayList;
import java.util.List;

public class WaypointsNaming {

    private static List<Point> internalWaypoints = new ArrayList<>();

    static {
        internalWaypoints.add(new Point( 42.3775960000000, -8.7333460000000, "AGUETE" ));
        internalWaypoints.add(new Point( 42.5443980000000, -8.8982890000000, "AREOSO" ));
        internalWaypoints.add(new Point( 42.3334670000000, -8.7973390000000, "BELUSO" ));
        internalWaypoints.add(new Point( 42.3299640000000, -8.7831230000000, "BUEU" ));
        internalWaypoints.add(new Point( 42.6174270000000, -8.8924900000000, "CABO DE CRUZ" ));
        internalWaypoints.add(new Point( 42.3966666670000, -8.9116666670000, "CAMOUCO" ));
        internalWaypoints.add(new Point( 42.4483830000000, -8.9159670000000, "CARDINAL SUR SAN VICENTE DO MAR" ));
        internalWaypoints.add(new Point( 42.4290460000000, -8.7034530000000, "COMBARRO" ));
        internalWaypoints.add(new Point( 42.4980670000000, -8.8577920000000, "EL GROVE" ));
        internalWaypoints.add(new Point( 42.6361450000000, -8.8999570000000, "ESCARABOTE" ));
        internalWaypoints.add(new Point( 42.5065670000000, -8.9322000000000, "ESQUEIROS ROJA" ));
        internalWaypoints.add(new Point( 42.5120170000000, -8.9388170000000, "ESQUEIROS VERDE" ));
        internalWaypoints.add(new Point( 42.4141170000000, -8.8956000000000, "FAGILDA" ));
        internalWaypoints.add(new Point( 42.6041620000000, -8.9318820000000, "LA PUEBLA" ));
        internalWaypoints.add(new Point( 42.4882060000000, -8.8917890000000, "MELOXO" ));
        internalWaypoints.add(new Point( 42.5158840000000, -8.9311510000000, "MEZOS" ));
        internalWaypoints.add(new Point( 42.3728666666666, -8.7836000000000, "MORRAZAN" ));
        internalWaypoints.add(new Point( 42.3479000000000, -8.8200333333333, "MOURISCA" ));
        internalWaypoints.add(new Point( 42.4724420000000, -9.0011920000000, "MUELLE DE SALVORA" ));
        internalWaypoints.add(new Point( 42.5675630000000, -8.8692230000000, "O XUFRE" ));
        internalWaypoints.add(new Point( 42.3769620000000, -8.9282780000000, "ONS" ));
        internalWaypoints.add(new Point( 42.3838833333333, -8.7407333333333, "OS PELADOS" ));
        internalWaypoints.add(new Point( 42.4044000000000, -8.8907000000000, "PICAMILLO" ));
        internalWaypoints.add(new Point( 42.4819330000000, -8.9467500000000, "POMBEIRO /!\\" ));
        internalWaypoints.add(new Point( 42.3934333333333, -8.7188333333333, "PORTOCELO" ));
        internalWaypoints.add(new Point( 42.3946020000000, -8.8179230000000, "PORTONOVO" ));
        internalWaypoints.add(new Point( 42.4451890000000, -8.9308050000000, "PUNTA MIRANDA" ));
        internalWaypoints.add(new Point( 42.4016220000000, -8.7540260000000, "RAXO" ));
        internalWaypoints.add(new Point( 42.5629340000000, -8.9869230000000, "RIBEIRA" ));
        internalWaypoints.add(new Point( 42.4498330000000, -8.9091500000000, "ROJA SAN VICENTE DO MAR" ));
        internalWaypoints.add(new Point( 42.5492240000000, -8.9395720000000, "RÚA" ));
        internalWaypoints.add(new Point( 42.4568340000000, -8.9171040000000, "SAN VICENTE DO MAR" ));
        internalWaypoints.add(new Point( 42.3958220000000, -8.7998720000000, "SANXENXO" ));
        internalWaypoints.add(new Point( 42.4515330000000, -8.9050670000000, "SINAL DE BALEA" ));
        internalWaypoints.add(new Point( 42.4067500000000, -8.7100166666667, "TAMBO" ));
        internalWaypoints.add(new Point( 42.6023570000000, -8.7752130000000, "VILLAGARCIA" ));
        internalWaypoints.add(new Point( 42.5664690000000, -8.8346460000000, "VILLANUEVA" ));
    }

    private WaypointsNaming() {
        // Not instantiable as it's a naming class
    }

    public static List<Point> getInternalWaypoints() {
        return internalWaypoints;
    }
}
