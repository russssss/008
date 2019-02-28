package com.example.t_008.feature_app;

import com.example.t_008.feature_app.currency_view.CurrencyViewModel;

import java.util.List;

public interface InteractorCurrencyListener {
    void onDataLoaded(List<CurrencyViewModel> currencyViewModelList);
    void onDataError();
}
