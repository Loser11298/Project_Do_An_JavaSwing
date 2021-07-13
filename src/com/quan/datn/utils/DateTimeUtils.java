/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTimeUtils {

    public static Date getDateInString(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException ex) {
            return new Date("01/01/1970");
        }
    }

    public static String getDateNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getDateTimeString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss-dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    
    public static String formatDate(Date date) {
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        return dtf.format(date);
    }

    public static String dateToDateTimeString(Date date) {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dtf.format(date);
    }

    public static String formatTimeString(String time) {
        try {
            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss");
            return dtf.format(df.parse(time));

        } catch (ParseException ex) {
            Logger.getLogger(Utils.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "00:00:00";
        }
    }

}
