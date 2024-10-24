package com.developer.geoips.utils;

import java.text.DecimalFormat;

public class Utilities {
    public static String doubleToString(double numeroDouble){
        DecimalFormat formato = new DecimalFormat("#.00");
        return formato.format(numeroDouble);
    }

}
