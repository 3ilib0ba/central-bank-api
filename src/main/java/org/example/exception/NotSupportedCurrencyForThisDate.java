package org.example.exception;

public class NotSupportedCurrencyForThisDate extends RuntimeException {
    public NotSupportedCurrencyForThisDate() {
        super("There's no info about this currency for this date");
    }
}
