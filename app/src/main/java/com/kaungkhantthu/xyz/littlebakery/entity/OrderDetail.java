package com.kaungkhantthu.xyz.littlebakery.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class OrderDetail extends RealmObject {

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("orderitem")
    @Expose
    private RealmList<Orderitem> orderitems;
    @SerializedName("deliveryaddress")
    @Expose
    private String deliveryAddress;
    @SerializedName("deliveryDate")
    @Expose
    private long deliveryDate;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phnumber")
    @Expose
    private String phnumber;
    @Ignore


    private int totalprice;

    public int getTotalprice() {
        int total = 0;
        for(Orderitem o :orderitems ){
            total +=  o.getPrice() * o.getQuantity();
        }
    totalprice = total;
        return totalprice;
    }
    public int getTotalqautity() {
        int total = 0;
        for(Orderitem o :orderitems ){
            total += o.getQuantity();
        }
        return total;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnumber() {
        return phnumber;
    }

    public void setPhnumber(String phnumber) {
        this.phnumber = phnumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RealmList<Orderitem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(RealmList<Orderitem> orderitems) {
        this.orderitems = orderitems;
    }
    public void setOrderitems(List<Orderitem> orderitems) {
        this.orderitems = (RealmList<Orderitem>) orderitems;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public long getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(long deliveryDateinmillisecond) {
        this.deliveryDate = deliveryDateinmillisecond;
    }

    @Override
    public String toString() {
        String orderitemlistString= "";
        for(Orderitem o:this.orderitems){
            orderitemlistString += o.getCakeName()+" "+" x "+o.getQuantity()+" \n";

        }
        return  orderitemlistString;
    }
}
