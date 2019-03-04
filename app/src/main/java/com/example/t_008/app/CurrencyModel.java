package com.example.t_008.app;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public class CurrencyModel {
    @Element(name = "NumCode")
    private int numCode;
    @Element(name = "CharCode")
    private String charCode;
    @Element(name = "Nominal")
    private int nominal;
    @Element(name = "Name")
    private String name;
    @Element(name = "Value")
    private String value;

    public int getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
