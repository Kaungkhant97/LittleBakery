package com.kaungkhantthu.xyz.littlebakery.entity;

import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class Orderitem extends RealmObject{

    private String cakeId;
    private String cakeName;
    private String cakeImgurl;
    private int quantity;
    private int price;

    public String getCakeId() {
        return cakeId;
    }

    public void setCakeId(String cakeId) {
        this.cakeId = cakeId;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCakeImgurl() {
        return cakeImgurl;
    }

    public void setCakeImgurl(String cakeImgurl) {
        this.cakeImgurl = cakeImgurl;
    }
}
