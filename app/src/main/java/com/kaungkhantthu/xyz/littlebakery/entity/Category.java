package com.kaungkhantthu.xyz.littlebakery.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category  extends RealmObject{

@SerializedName("__v")
@Expose
private Integer v;
@SerializedName("name")
@Expose
private String name;

@SerializedName("_id")
@Expose
@PrimaryKey
private Integer id;

public Integer getV() {
return v;
}

public void setV(Integer v) {
this.v = v;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

}