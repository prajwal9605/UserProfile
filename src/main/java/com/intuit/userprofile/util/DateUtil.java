package com.intuit.userprofile.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author prajwal.kulkarni on 18/09/21
 */
public class DateUtil {

    public static Date addSeconds(Date date, int numberOfSeconds) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, numberOfSeconds);
        return calendar.getTime();
    }

}
