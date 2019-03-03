package com.example.t_008.app;

import com.example.t_008.app.currency_view.CurrencyViewModel;

import java.util.List;

public interface PresenterCurrencyListener {
    void onDataLoaded(List<CurrencyViewModel> currencyViewModelList);

    void onChangedTo(List<CurrencyViewModel> currencyViewModelListTo);

    void calculateCurrency();

    void showCurrencyTo(String currency);

    void onDataError();
}
