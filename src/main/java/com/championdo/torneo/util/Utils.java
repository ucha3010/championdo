package com.championdo.torneo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String date2String (Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }
}
