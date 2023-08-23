package com.driver;

import io.swagger.models.auth.In;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id=id;
        int hh= Integer.parseInt(deliveryTime.substring(0,2));
        int mm=Integer.parseInt(deliveryTime.substring(3));
        this.deliveryTime=hh*60+mm;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        String charArr[]=deliveryTime.split(":");
        int hh=Integer.parseInt(charArr[0]);
        int mm=Integer.parseInt(charArr[1]);
        this.deliveryTime=hh*60+mm;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

}
