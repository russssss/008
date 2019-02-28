package com.example.t_008.feature_app;

import com.example.t_008.feature_app.currency_view.CurrencyViewModel;

import java.util.List;

public interface PresenterCurrencyListener {
    void onDataLoaded(List<CurrencyViewModel> currencyViewModelList);

    void onChangedTo(List<CurrencyViewModel> currencyViewModelListTo);

    void calculateCurrency();

    void showCurrencyTo(String currency);

    void onDataError();
}
