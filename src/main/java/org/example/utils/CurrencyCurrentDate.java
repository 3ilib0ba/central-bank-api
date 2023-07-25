package org.example.utils;

import java.util.Date;

public class CurrencyCurrentDate {
    private Currency currency;

    private Date date;

    private Double nominal;

    private Double value;

    public CurrencyCurrentDate() {
    }

    public CurrencyCurrentDate(Currency currency, Date date, Double nominal, Double value) {
        this.currency = currency;
        this.date = date;
        this.nominal = nominal;
        this.value = value;
    }

    public String toStringFormat() {
        // цена за 1 единицу валюты
        String valForOne = String.valueOf(value / nominal).replace('.', ',');
        return currency.getCode() + " (" + currency.getName() + "): " + valForOne;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
