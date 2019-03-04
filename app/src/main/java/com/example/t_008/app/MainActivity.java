package com.example.t_008.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t_008.R;
import com.example.t_008.app.currency_view.CurrencyView;
import com.example.t_008.app.currency_view.CurrencyViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CurrencyViewListener, PresenterCurrencyListener, IContext {

    private EditText currencySumFrom;
    private TextView currencySumTo;
    private CurrencyView currencyViewFrom;
    private CurrencyView currencyViewTo;
    private PresenterCurrency presenterCurrency;
    private String VIEW_PAGER_POS_FROM = "POS_FROM";
    private String VIEW_PAGER_POS_TO = "POS_TO";

    ///////////////////////////////////////////////////////////////////////////
    // Activity
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenterCurrency.start();
    }

    @Override
    protected void onStop() {
        presenterCurrency.stop();
        super.onStop();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(VIEW_PAGER_POS_FROM) &&
                savedInstanceState.containsKey(VIEW_PAGER_POS_TO)) {
            currencyViewFrom.setPosition(savedInstanceState.getInt(VIEW_PAGER_POS_FROM));
            currencyViewTo.setPosition(savedInstanceState.getInt(VIEW_PAGER_POS_TO));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(VIEW_PAGER_POS_FROM, currencyViewFrom.getCurrentPosition());
        outState.putInt(VIEW_PAGER_POS_TO, currencyViewTo.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Methods
    ///////////////////////////////////////////////////////////////////////////

    private void initUI() {
        currencySumFrom = findViewById(R.id.currency_sum_from);
        currencySumTo = findViewById(R.id.currency_sum_to);
        currencyViewFrom = findViewById(R.id.currency_from);
        currencyViewTo = findViewById(R.id.currency_to);
    }

    private void init() {

        presenterCurrency = new PresenterCurrency(this, this);

        currencySumFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenterCurrency.calculateCurrency(currencyViewFrom.getCurrency(), currencyViewTo.getCurrency(), currencySumFrom.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        currencyViewFrom.setCurrencyViewListener(this);
        currencyViewTo.setCurrencyViewListener(new CurrencyViewListener() {
            @Override
            public void onCurrencySelected(CurrencyViewModel currency) {
                presenterCurrency.productSelectedTo();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // CurrencyViewListener
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onCurrencySelected(CurrencyViewModel currency) {
        presenterCurrency.productSelectedFrom(currency);
    }

    ///////////////////////////////////////////////////////////////////////////
    // PresenterCurrencyListener
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onDataLoaded(List<CurrencyViewModel> currencyViewModelList) {
        currencyViewFrom.setData(currencyViewModelList);
        presenterCurrency.productSelectedFrom(currencyViewModelList.get(currencyViewFrom.getCurrentPosition()));
    }

    @Override
    public void onChangedTo(List<CurrencyViewModel> currencyViewModelListTo) {
        currencyViewTo.setData(currencyViewModelListTo);
    }

    @Override
    public void calculateCurrency() {
        presenterCurrency.calculateCurrency(currencyViewFrom.getCurrency(), currencyViewTo.getCurrency(), currencySumFrom.getText().toString());
    }

    @Override
    public void showCurrencyTo(String s) {
        currencySumTo.setText(s);
    }

    @Override
    public void onDataError() {
        Toast.makeText(getContext(), R.string.error_data_loading, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
