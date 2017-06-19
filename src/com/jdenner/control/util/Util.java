package com.jdenner.control.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class Util {

    public static final LocalDate toLocalDate(Date date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return LocalDate.parse(sdf.format(date), dtf);
    }

    public static Date toDate(LocalDate value) throws ParseException {
        if (value == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(value.toString());
    }

}
