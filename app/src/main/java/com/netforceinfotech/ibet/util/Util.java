package com.netforceinfotech.ibet.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Netforce on 9/13/2016.
 */
public class Util {
    public static String getDateCurrentTimeZone(long timestamp) {
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
            sfd.setTimeZone(Calendar.getInstance().getTimeZone());
            String date = sfd.format(new Date(timestamp));
            Log.i("localtime", timestamp + "");
            // date = sfd.getCalendar().getTime().toString();
            return date;

        } catch (Exception e) {
            return "";
        }

    }

    public static String getTimeCurrentTimeZone(long timestamp) {
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("hh:mm a");
            sfd.setTimeZone(Calendar.getInstance().getTimeZone());
            String date = sfd.format(new Date(timestamp));
            Log.i("localtime", timestamp + "");
            // date = sfd.getCalendar().getTime().toString();
            return date;

        } catch (Exception e) {
            return "";
        }

    }

}
