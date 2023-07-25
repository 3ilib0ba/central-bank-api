package org.example.utils;

public class DateAndCode {
    private String code;
    private String date;

    public DateAndCode(String date, String code) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "DateAndCode {" +
                " date='" + date + '\'' +
                ", code='" + code + '\'' +
                " }";
    }
}
