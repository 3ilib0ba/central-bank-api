package org.example.exception;

public class IncorrectDateFormatException extends RuntimeException {
    public IncorrectDateFormatException() {
        super("Incorrect date format. Use this format: --date=<YYYY-MM-DD>. Example: 2001-06-27");
    }
}
