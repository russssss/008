package com.example.t_008.utils;

import android.util.Log;

import com.example.t_008.feature_app.CurrencyListModel;
import com.example.t_008.feature_app.CurrencyModel;
import com.example.t_008.feature_storage.CurrencyDbModel;

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

            for (CurrencyModel currencyModel : pet.currencyModelList) {
                currencyDbModelList.add(new CurrencyDbModel.Builder()
                        .numCode(currencyModel.numCode)
                        .charCode(currencyModel.charCode)
                        .nominal(currencyModel.nominal)
                        .name(currencyModel.name)
                        .value(currencyModel.value)
                        .build());
            }

            return currencyDbModelList;
        } catch (Exception e) {
            Log.e("msg", e.getMessage());
        }
        return null;
    }
}
