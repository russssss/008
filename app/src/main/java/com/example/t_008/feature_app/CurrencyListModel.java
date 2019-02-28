package com.example.t_008.feature_app;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class CurrencyListModel {
    @ElementList(inline = true)
    public List<CurrencyModel> currencyModelList;
}