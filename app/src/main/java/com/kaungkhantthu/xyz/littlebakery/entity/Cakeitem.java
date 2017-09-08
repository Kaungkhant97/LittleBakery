
package com.kaungkhantthu.xyz.littlebakery.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cakeitem extends RealmObject {

    @SerializedName("_id")
    @Expose
    @PrimaryKey
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imgurl")
    @Expose
    private String imgurl;
    @SerializedName("catid")
    @Expose
    private int catid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("nutrition")
    @Expose
    private Nutrition nutrition;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("ingredient")
    @Expose
    private RealmList<Ingredient> ingredient = null;
    private int quantity = 0;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public RealmList<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient( RealmList<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

}
