package com.example.cryptopricetracker;

public class CurrencyRvModel {
    private String name;
    private String sybmol;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSybmol() {
        return sybmol;
    }

    public void setSybmol(String sybmol) {
        this.sybmol = sybmol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CurrencyRvModel(String name, String sybmol, double price) {
        this.name = name;
        this.sybmol = sybmol;
        this.price = price;
    }
}
