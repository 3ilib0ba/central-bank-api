package org.example.utils;

import org.example.exception.IncorrectArgsFormatException;
import org.example.exception.NotAllowedArgException;

public class ArgsHandler {
    public DateAndCode handleArgsAndGetCorrectDateAndCode(String[] args) {
        checkArgsLength(args);
        return getValuesFromArgs(args);
    }

    private void checkArgsLength(String[] args) {
        if (args.length != 2) {
            throw new IncorrectArgsFormatException();
        }
    }

    private DateAndCode getValuesFromArgs(String[] args) {
        String dateStr = "";
        String currencyCode = "";

        for (String arg : args) {
            if (arg.startsWith("--code=")) {
                currencyCode = arg.substring(7);
            } else if (arg.startsWith("--date=")) {
                dateStr = arg.substring(7);
            } else {
                throw new NotAllowedArgException();
            }
        }

        return new DateAndCode(dateStr, currencyCode);
    }

}
