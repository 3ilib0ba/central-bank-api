package org.example.exception;

public class NotAllowedArgException extends RuntimeException {
    public NotAllowedArgException() {
        super("You entered not allowed arg(-s) name. Please use this format: currency_rates --code=<CODE> --date=<YYYY-MM-DD>");
    }
}
