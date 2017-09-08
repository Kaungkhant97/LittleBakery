
package com.kaungkhantthu.xyz.littlebakery.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("__v")
@Expose
private Integer v;
@SerializedName("name")
@Expose
private String name;



    @SerializedName("phnumber")

@Expose
private String phnumber;
@SerializedName("address")
@Expose
private String address;
@SerializedName("imgurl")
@Expose
private String imgurl;
@SerializedName("_id")
@Expose
private String id;

    public User(String name, String phnumber, String address) {
        this.name = name;
        this.phnumber = phnumber;
        this.address = address;
    }

    public User(String name, String phnumber, String address, String imgurl) {
        this.name = name;
        this.phnumber = phnumber;
        this.address = address;
        this.imgurl = imgurl;
    }

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

public String getPhnumber() {
return phnumber;
}

public void setPhnumber(String phnumber) {
this.phnumber = phnumber;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getImgurl() {
return imgurl;
}

public void setImgurl(String imgurl) {
this.imgurl = imgurl;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

}