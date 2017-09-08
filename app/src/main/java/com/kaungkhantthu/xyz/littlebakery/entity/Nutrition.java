
package com.kaungkhantthu.xyz.littlebakery.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Nutrition extends RealmObject {

    @SerializedName("calories")
    @Expose
    private Integer calories;
    @SerializedName("fat")
    @Expose
    private Integer fat;
    @SerializedName("cholesterol")
    @Expose
    private Integer cholesterol;
    @SerializedName("sugar")
    @Expose
    private Integer sugar;
    @SerializedName("protein")
    @Expose
    private Integer protein;
    @SerializedName("_id")
    @Expose
    private String id;

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Integer getSugar() {
        return sugar;
    }

    public void setSugar(Integer sugar) {
        this.sugar = sugar;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
