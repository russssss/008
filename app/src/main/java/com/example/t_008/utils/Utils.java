package com.example.t_008.utils;

import android.util.Log;

import com.example.t_008.app.CurrencyListModel;
import com.example.t_008.app.CurrencyModel;
import com.example.t_008.storage.CurrencyDbModel;

import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<CurrencyDbModel> deserilize(String xml) {
        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();
        try {
            CurrencyListModel pet = serializer.read(CurrencyListModel.class, reader, false);
            List<CurrencyDbModel> currencyDbModelList = new ArrayList<>();

            for (CurrencyModel currencyModel : pet.getCurrencyModelList()) {
                currencyDbModelList.add(new CurrencyDbModel.Builder()
                        .numCode(currencyModel.getNumCode())
                        .charCode(currencyModel.getCharCode())
                        .nominal(currencyModel.getNominal())
                        .name(currencyModel.getName())
                        .value(currencyModel.getValue())
                        .build());
            }

            return currencyDbModelList;
        } catch (Exception e) {
            Log.e("msg", e.getMessage());
        }
        return null;
    }
}
