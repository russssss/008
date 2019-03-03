package com.example.t_008.app.currency_view;

import java.math.BigDecimal;

public class CurrencyViewModel {

    private String displayCurrency;
    private int nominal;
    private BigDecimal sum;

    public CurrencyViewModel(String displayCurrency, int nominal, BigDecimal sum) {
        this.displayCurrency = displayCurrency;
        this.nominal = nominal;
        this.sum = sum;
    }

    public String getDisplayCurrency() {
        return displayCurrency;
    }

    public int getNominal() {
        return nominal;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
