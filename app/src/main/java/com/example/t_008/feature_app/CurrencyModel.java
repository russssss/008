package com.example.t_008.feature_app;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public class CurrencyModel {
    @Element(name = "NumCode")
    public int numCode;
    @Element(name = "CharCode")
    public String charCode;
    @Element(name = "Nominal")
    public int nominal;
    @Element(name = "Name")
    public String name;
    @Element(name = "Value")
    public String value;
}
