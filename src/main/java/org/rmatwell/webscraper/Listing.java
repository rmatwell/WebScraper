package org.rmatwell.webscraper;

/**
 * @author Richard Atwell
 */
public class Listing {

    private String model;
    private String chipSet;
    private int id;
    private double price;
    private String url;

    public Listing(String model, String chipSet, int id, double price, String url) {
        this.model = model;
        this.chipSet = chipSet;
        this.id = id;
        this.price = price;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
       String s = (id + "  -  " + price + "  -  " + chipSet + " - " + model +  "  -  " + url);
        return s;
    }
}
