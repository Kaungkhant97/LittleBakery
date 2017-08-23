package com.kaungkhantthu.xyz.littlebakery.entity;

import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class Ingredient extends RealmObject {
    private String name;

    public Ingredient() {

    }
    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
