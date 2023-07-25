package org.example.utils;

import org.example.exception.IncorrectDateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransformer {
    static SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat centralBankDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Date getDateFromInputFormat(String dateStr) {
        try {
            return inputDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IncorrectDateFormatException();
        }
    }

    public static String getDateToCBFormat(Date date) {
        return centralBankDateFormat.format(date);
    }

}
