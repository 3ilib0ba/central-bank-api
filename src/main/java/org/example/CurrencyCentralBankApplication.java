package org.example;

import org.example.bank_api.CentralBankApiRunner;
import org.example.utils.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class CurrencyCentralBankApplication {
    private ArgsHandler argsHandler;
    private CentralBankApiRunner centralBankApiRunner;
    private String[] args;

    public CurrencyCentralBankApplication(String[] args) {
        this.argsHandler = new ArgsHandler();
        this.centralBankApiRunner = new CentralBankApiRunner();
        this.args = args;
    }

    public static void main(String[] args) {
        CurrencyCentralBankApplication system = new CurrencyCentralBankApplication(args);
        system.run();
    }

    private void run() {
        DateAndCode dateAndCode = argsHandler.handleArgsAndGetCorrectDateAndCode(args);

        Date date = DateTransformer.getDateFromInputFormat(dateAndCode.getDate());
        Currency currency = centralBankApiRunner.findCurrencyByIsoCode(dateAndCode.getCode());

        CurrencyCurrentDate currencyCurrentDate = centralBankApiRunner.getCurrencyValueFromCurrentDate(date, currency);
        System.out.println(currencyCurrentDate.toStringFormat());
    }
}