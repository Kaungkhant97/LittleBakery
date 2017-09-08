
package com.kaungkhantthu.xyz.littlebakery.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class CakeitemResponse extends RealmObject{

    @SerializedName("data")
    @Expose
    private RealmList<Cakeitem> data = null;

    public RealmList<Cakeitem> getData() {
        return data;
    }

    public void setData(RealmList<Cakeitem> data) {
        this.data = data;
    }

}
