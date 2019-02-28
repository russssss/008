package com.example.t_008.feature_app;

import com.example.t_008.feature_thread.CustomObserver;
import com.example.t_008.feature_thread.CustomThread;
import com.example.t_008.feature_app.currency_view.CurrencyViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PresenterCurrency implements InteractorCurrencyListener{

    private InteractorCurrency interactorCurrency;
    private PresenterCurrencyListener presenterCurrencyListener;
    private List<CurrencyViewModel> currencyViewModelList;

    public PresenterCurrency(PresenterCurrencyListener presenterCurrencyListener, IContext iContext) {
        this.presenterCurrencyListener = presenterCurrencyListener;
        interactorCurrency = new InteractorCurrency(this, iContext);
    }

    public void start() {
        interactorCurrency.requestData();
    }

    public void stop() {
        interactorCurrency.restStop();
    }

    public void productSelectedFrom(final CurrencyViewModel currencyViewModel) {

        if (currencyViewModelList == null)
            return;


        final CustomThread myCustomThread = new CustomThread();
        myCustomThread.subscribe(new CustomObserver<List<CurrencyViewModel>>() {
            @Override
            public void callOnSuccess(List<CurrencyViewModel> o) {
                presenterCurrencyListener.onChangedTo(o);
                presenterCurrencyListener.calculateCurrency();
            }

            @Override
            public List<CurrencyViewModel> doOnThread() {

                List<CurrencyViewModel> currencyViewModelListTo = new ArrayList<>();
                for (CurrencyViewModel currencyViewModelElement : currencyViewModelList) {
                    if (!currencyViewModel.getDisplayCurrency().equals(currencyViewModelElement.getDisplayCurrency())) {
                        currencyViewModelListTo.add(currencyViewModelElement);
                    }
                }

                return currencyViewModelListTo;
            }
        });
    }

    public void productSelectedTo() {
        presenterCurrencyListener.calculateCurrency();
    }

    public void calculateCurrency(CurrencyViewModel currencyFrom, CurrencyViewModel currencyTo, String s) {
        double val = s.trim().length() == 0 ? 0 : Double.valueOf(s);

        if (currencyFrom == null || currencyTo == null)
            return;

        double from = currencyFrom.getNominal();
        BigDecimal fromSum = currencyFrom.getSum();
        double to = currencyTo.getNominal();
        BigDecimal toSum = currencyTo.getSum();

        BigDecimal v = BigDecimal.valueOf(((to / toSum.doubleValue()) / (from / fromSum.doubleValue())) * val);

        presenterCurrencyListener.showCurrencyTo(String.valueOf(v.setScale(2, RoundingMode.CEILING)));
    }

    ///////////////////////////////////////////////////////////////////////////
    // InteractorCurrencyListener
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onDataLoaded(List<CurrencyViewModel> currencyViewModelList) {
        this.currencyViewModelList = currencyViewModelList;
        presenterCurrencyListener.onDataLoaded(currencyViewModelList);
    }

    @Override
    public void onDataError() {
        presenterCurrencyListener.onDataError();
    }
}
