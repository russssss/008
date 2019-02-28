package com.example.t_008;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.t_008.feature_app.IContext;
import com.example.t_008.feature_app.InteractorCurrency;
import com.example.t_008.feature_app.InteractorCurrencyListener;
import com.example.t_008.feature_app.MainActivity;
import com.example.t_008.feature_app.currency_view.CurrencyViewModel;
import com.example.t_008.feature_thread.CustomThread;

import org.junit.After;
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
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.t_008", appContext.getPackageName());
    }

    @Test
    public void interactorTest() {
        assertNotNull(interactorCurrencyListener);
        assertNotNull(iContext);
        assertEquals(iContext.getContext(), mActivityRule.getActivity().getContext());

        InteractorCurrency interactorCurrency = new InteractorCurrency(interactorCurrencyListener, iContext);
        assertNotNull(interactorCurrency);
        interactorCurrency.requestData();

    }

    @After
    public void interactorTestAfter() {
        InteractorCurrency interactorCurrency = new InteractorCurrency(interactorCurrencyListener, iContext);
        interactorCurrency.restStop();
    }
}
