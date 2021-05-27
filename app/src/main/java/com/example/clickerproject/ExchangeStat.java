package com.example.clickerproject;

public class ExchangeStat {
    private final int id;
    private float price;
    private float exchangePrice;
    private float defExchangePrice;
    private final int imgLink;
    private final String name;


    public ExchangeStat(int id, String name, float price, float exchangePrice,float defExchangePrice ,int imgLink) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.exchangePrice = exchangePrice;
        this.imgLink = imgLink;
        this.defExchangePrice = defExchangePrice;
    }

    public String getExchangePrice() {return String.valueOf(exchangePrice);}

    public float getFloatExchangePrice() {return exchangePrice;}

    public void setExchangePrice(float e) {this.exchangePrice = e;}

    public void setDefExchangePrice(float d){this.defExchangePrice = d;}

    public float getDefExchangePrice(){return defExchangePrice;}

    public int getId() {
        return id;
    }

    public void setPrice(float p){
        this.price = p;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public float getFloatPrice(){
        return price;
    }

    public int getImgLink() {
        return imgLink;
    }

}
