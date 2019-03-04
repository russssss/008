package com.example.t_008.app;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class CurrencyListModel {
    @ElementList(inline = true)
    private List<CurrencyModel> currencyModelList;

    public List<CurrencyModel> getCurrencyModelList() {
        return currencyModelList;
    }
}