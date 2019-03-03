package com.example.t_008.app;

import com.example.t_008.app.currency_view.CurrencyViewModel;

import java.util.List;

public interface InteractorCurrencyListener {
    void onDataLoaded(List<CurrencyViewModel> currencyViewModelList);
    void onDataError();
}
