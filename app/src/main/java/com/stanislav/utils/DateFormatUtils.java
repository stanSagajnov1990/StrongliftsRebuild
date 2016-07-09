package com.stanislav.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Stanislav on 01.07.2016.
 */
public class DateFormatUtils {

    public static String getDayOfWeek(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE", Locale.US);
        return formatter.format(date);
    }

    public static String formatdMMMyyyy(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy", Locale.US);
        return formatter.format(date);
    }

    public static String formatddMMyyyy(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        return formatter.format(date);
    }

    public static Date parseddMMyyyy(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = sdf.parse(text);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
