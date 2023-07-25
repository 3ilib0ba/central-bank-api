package org.example.exception;

public class IncorrectArgsFormatException extends RuntimeException {
    public IncorrectArgsFormatException() {
        super("Correct usage: currency_rates --code=<CODE> --date=<YYYY-MM-DD>");
    }
}
