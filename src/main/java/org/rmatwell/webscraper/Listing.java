package org.rmatwell.webscraper;

import java.io.Serializable;

/**
 * @author Richard Atwell
 */

public class Listing implements Serializable {

    private String model;
    private String chipSet;
    private int id;
    private double price;
    private String brand;
    private String url;

    public Listing(){}

    public Listing(int id, String model, String brand, double price, String chipSet, String url) {
        this.model = model;
        this.chipSet = chipSet;
        this.id = id;
        this.price = price;
        this.brand = brand;
        this.url = url;
    }

    public void setModel(String s){
        model = s;
    }

    public String getModel() {
        return model;
    }

    public String getChipSet() {
        return chipSet;
    }

    public void setChipSet(String chipSet) {
        this.chipSet = chipSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendVal(sb, String.valueOf(id));
        appendVal(sb, model);
        appendVal(sb, chipSet);
        appendVal(sb, String.valueOf(price));
        appendVal(sb, brand);
        appendVal(sb, url);

        return sb.toString();
    }

    private void appendVal(StringBuilder sb, String value) {
        if(value != null) { sb.append(value).append(","); }
        else { sb.append(","); }
    }
}
