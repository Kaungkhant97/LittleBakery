package com.kaungkhantthu.xyz.littlebakery.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class CategoryResponse extends RealmObject {

    @SerializedName("data")
    @Expose
    private RealmList<Category> data = null;

    public RealmList<Category> getData() {
        return data;
    }

    public void setData(RealmList<Category> data) {
        this.data = data;
    }

}

