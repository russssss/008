package com.example.t_008.app;

import android.content.Context;

import com.example.t_008.thread.CustomThread;
import com.example.t_008.thread.CustomObserver;
import com.example.t_008.app.currency_view.CurrencyViewModel;
import com.example.t_008.storage.CurrencyDbModel;
import com.example.t_008.storage.Storage;
import com.example.t_008.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class InteractorCurrency {

    private InteractorCurrencyListener interactorCurrencyListener;
    private Storage storage;
    private IContext iContext;
    private CustomThread myCustomThread;

    public InteractorCurrency(InteractorCurrencyListener interactorCurrencyListener, IContext iContext) {
        this.interactorCurrencyListener = interactorCurrencyListener;
        storage = new Storage(iContext); // не буду проверять iContext instanceof Context
        this.iContext = iContext;
    }

    public void requestData() {

        myCustomThread = new CustomThread();
        myCustomThread.subscribe(new CustomObserver<List<CurrencyDbModel>>() {
            @Override
            public void callOnSuccess(List<CurrencyDbModel> list) {

                if (list == null) {
                    interactorCurrencyListener.onDataError();
                } else {

                    final List<CurrencyViewModel> currencyViewModelList = new ArrayList<>();

                    for (CurrencyDbModel currencyDbModel : list) {
                        currencyViewModelList.add(new CurrencyViewModel(currencyDbModel.getCharCode(), currencyDbModel.getNominal(), BigDecimal.valueOf(Double.valueOf(currencyDbModel.getValue().replace(",", ".")))));
                    }

                    interactorCurrencyListener.onDataLoaded(currencyViewModelList);
                }
            }

            @Override
            public List<CurrencyDbModel> doOnThread() {
                try {
                    return doRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    public void requestStop() {
        if (myCustomThread != null) {
            myCustomThread.unsubscribe();
        }
    }

    private List<CurrencyDbModel> doRequest() throws Exception {

        if (((App) iContext.getContext().getApplicationContext()).isDataLoaded()) {
            return storage.readData();
        }

        List<CurrencyDbModel> list;
        URL githubEndpoint = new URL("https://www.cbr.ru/scripts/XML_daily.asp");
        HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
        myConnection.setRequestProperty("User-Agent", "bank");

        if (myConnection.getResponseCode() == 200) {

            myConnection.connect();
            InputStreamReader in = new InputStreamReader((InputStream) myConnection.getContent());
            BufferedReader buff = new BufferedReader(in);
            String line;
            final StringBuilder stringBuilder = new StringBuilder();

            do {
                line = buff.readLine();
                stringBuilder.append(line + "\n");
            } while (line != null);

            list = Utils.deserilize(stringBuilder.toString());

            ((App) iContext.getContext().getApplicationContext()).setDataLoaded(true);

            storage.saveData(list);
        } else {
            list = storage.readData();
        }

        return list;
    }

}
