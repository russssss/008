package com.example.t_008.storage;

public class CurrencyDbModel {
    private int numCode;
    private String charCode;
    private int nominal;
    private String name;
    private String value;

    private CurrencyDbModel(Builder builder) {
        numCode = builder.numCode;
        charCode = builder.charCode;
        nominal = builder.nominal;
        name = builder.name;
        value = builder.value;
    }

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

    public static final class Builder {
        private int numCode;
        private String charCode;
        private int nominal;
        private String name;
        private String value;

        public Builder() {
        }

        public Builder numCode(int val) {
            numCode = val;
            return this;
        }

        public Builder charCode(String val) {
            charCode = val;
            return this;
        }

        public Builder nominal(int val) {
            nominal = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public CurrencyDbModel build() {
            return new CurrencyDbModel(this);
        }
    }
}
