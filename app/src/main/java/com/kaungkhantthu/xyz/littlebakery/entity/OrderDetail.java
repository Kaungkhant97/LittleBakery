package com.kaungkhantthu.xyz.littlebakery.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class OrderDetail extends RealmObject {

    private String userId;
    private RealmList<Orderitem> orderitems;
    private String deliveryAddress;
    private String deliveryDate;

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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
