package com.example.t_008;

import android.content.Context;

import com.example.t_008.app.IContext;
import com.example.t_008.app.InteractorCurrency;
import com.example.t_008.app.InteractorCurrencyListener;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class InteractorCurrencyTestMock {

    @Test
    public void context(){
        InteractorCurrency interactorCurrency = Mockito.mock(InteractorCurrency.class);
        interactorCurrency.requestData();
        interactorCurrency.requestStop();

        Mockito.verify(interactorCurrency).requestData();
        Mockito.verify(interactorCurrency).requestStop();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

}
