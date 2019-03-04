package com.example.t_008;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.t_008.app.IContext;
import com.example.t_008.app.InteractorCurrency;
import com.example.t_008.app.InteractorCurrencyListener;
import com.example.t_008.app.MainActivity;
import com.example.t_008.app.currency_view.CurrencyViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InteractorCurrencyTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    InteractorCurrencyListener interactorCurrencyListener;
    IContext iContext;
    InteractorCurrency interactorCurrency;

    @Before
    public void init() {

        interactorCurrencyListener = new InteractorCurrencyListener() {
            @Override
            public void onDataLoaded(List<CurrencyViewModel> currencyViewModelList) {
                assertNotNull(currencyViewModelList);
            }

            @Override
            public void onDataError() {

            }
        };

        iContext = new IContext() {
            @Override
            public Context getContext() {
                return mActivityRule.getActivity();
            }
        };

        interactorCurrency = new InteractorCurrency(interactorCurrencyListener, iContext);
    }

    @Test
    public void interactorTest() {
        assertNotNull(interactorCurrencyListener);
        assertNotNull(iContext);
        assertEquals(iContext.getContext(), mActivityRule.getActivity().getContext());
        assertNotNull(interactorCurrency);

        interactorCurrency.requestData(); interactorCurrency.requestStop();
        assertNotNull(iContext);
    }
}
